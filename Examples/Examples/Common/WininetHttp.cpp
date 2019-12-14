//#include "stdafx.h"
#include "WininetHttp.h"
#include <json.h>
#include <fstream>
#include <cstdio>
#include <tchar.h>
#include <iostream>
#include "spdlog/spdlog.h"
#pragma comment(lib,"Wininet.lib")

CWininetHttp::CWininetHttp(void) :m_hSession(NULL), m_hConnect(NULL), m_hRequest(NULL)
{
	spdlog::info("new CWininetHttp()");
}

CWininetHttp::~CWininetHttp(void)
{
	Release();
}

//通过HTTP请求：Get或Post方式获取JSON信息
const std::string CWininetHttp::RequestJsonInfo(const std::string &lpUrl
	, HttpRequest type
	, std::string strHeader
	, std::string strPostData)
{
	spdlog::info("invoke RequestJsonInfo()");
	std::string strRet = "";
	try
	{
		if (lpUrl.empty())
		{
			spdlog::error("throw Hir_ParamErr");
			throw Hir_ParamErr;
		}
		Release();
		//局部
		m_hSession = InternetOpen(_T("Http-connect"), INTERNET_OPEN_TYPE_PRECONFIG, NULL, NULL, NULL);
		if (NULL == m_hSession)
		{
			spdlog::error("throw Hir_InitErr");
			throw Hir_InitErr;
		}
		INTERNET_PORT port = INTERNET_DEFAULT_HTTP_PORT;
		std::string strHostName = "";
		std::string strPageName = "";

		ParseWebURL(lpUrl, strHostName, strPageName, port);

		spdlog::info("lpUrl:{}", lpUrl.c_str());
		spdlog::info("strHostName:{}", strHostName.c_str());
		spdlog::info("strPageName:{}", strPageName.c_str());
		spdlog::info("port:{}", (int)port);

		m_hConnect = InternetConnectA(m_hSession, strHostName.c_str(), port, NULL, NULL, INTERNET_SERVICE_HTTP, NULL, NULL);
		if (NULL == m_hConnect)
		{
			spdlog::error("throw Hir_ConnectErr");
			throw Hir_ConnectErr;
		}
		std::string strRequestType;
		if (Hr_Get == type)
		{
			strRequestType = "GET";
		}
		else
		{
			strRequestType = "POST";
		}
		m_hRequest = HttpOpenRequestA(m_hConnect, strRequestType.c_str(), strPageName.c_str(), "HTTP/1.1", NULL, NULL, INTERNET_FLAG_RELOAD, NULL);
		if (NULL == m_hRequest)
		{
			spdlog::error("throw Hir_InitErr");
			throw Hir_InitErr;
		}
		DWORD dwHeaderSize = (strHeader.empty()) ? 0 : strlen(strHeader.c_str());
		BOOL bRet = FALSE;
		spdlog::info("strHeader:{}", strHeader.c_str());
		spdlog::info("dwHeaderSize:{}", (int)dwHeaderSize);
		if (Hr_Get == type)
		{
			bRet = HttpSendRequestA(m_hRequest, strHeader.c_str(), dwHeaderSize, NULL, 0);
		}
		else
		{
			DWORD dwSize = (strPostData.empty()) ? 0 : strlen(strPostData.c_str());
			spdlog::info("strPostData:{}", strPostData.c_str());
			spdlog::info("dwSize:{}", (int)dwSize);

			bRet = HttpSendRequestA(m_hRequest, strHeader.c_str(), dwHeaderSize,
				(LPVOID)strPostData.c_str(), dwSize);
		}
		if (!bRet)
		{
			spdlog::error("throw Hir_SendErr");
			throw Hir_SendErr;
		}
		char szBuffer[READ_BUFFER_SIZE + 1] = { 0 };
		DWORD dwReadSize = READ_BUFFER_SIZE;
		if (!HttpQueryInfoA(m_hRequest, HTTP_QUERY_RAW_HEADERS, szBuffer, &dwReadSize, NULL))
		{
			spdlog::error("throw Hir_QueryErr");
			throw Hir_QueryErr;
		}
		if (NULL != strstr(szBuffer, "404"))
		{
			spdlog::error("throw 404");
			throw Hir_404;
		}
		while (true)
		{
			bRet = InternetReadFile(m_hRequest, szBuffer, READ_BUFFER_SIZE, &dwReadSize);
			if (!bRet || (0 == dwReadSize))
			{
				break;
			}
			szBuffer[dwReadSize] = '\0';
			strRet.append(szBuffer);
			char* res = new char[strlen(strRet.c_str()) + 1];
			strcpy(res, strRet.c_str());
			spdlog::info("response json:{}", res);
		}

	}
	catch (HttpInterfaceError error)
	{
		m_error = error;
	}

	return std::move(strRet);
}

// 解析Json数据
Json::Value CWininetHttp::ParseJsonInfo(const std::string &strJsonInfo)
{
	Json::Reader reader; //解析Json使用Json::Reader
	Json::Value value; //可以代表任何类型
	if (!reader.parse(strJsonInfo, value))
		spdlog::critical("CXLDbDataMgr::GetVideoGisData] Video Gis parse data error...");
	else
		return value;
}


// 解析URL地址
void CWininetHttp::ParseWebURL(std::string lpUrl, std::string &strHostName
	, std::string &strPageName, WORD &sPort)
{
	sPort = 80;
	string strTemp(lpUrl);
	std::size_t nPos = strTemp.find("http://");
	if (nPos != std::string::npos)
	{
		strTemp = strTemp.substr(nPos + 7, strTemp.size() - nPos - 7);
	}

	nPos = strTemp.find('/');
	if (nPos == std::string::npos)
	{
		strHostName = strTemp;
	}
	else
	{
		strHostName = strTemp.substr(0, nPos);
	}
	spdlog::info("using strHostName:{}", strHostName.c_str());
	std::size_t nPos1 = strHostName.find(':');
	if (nPos1 != std::string::npos)
	{
		std::string strPort = strTemp.substr(nPos + 1, strHostName.size() - nPos - 1);
		strHostName = strHostName.substr(0, nPos1);
		spdlog::info("using strHostName:{}", strHostName.c_str());
		sPort = (WORD)atoi(strPort.c_str());
		spdlog::info("using sPort:{}", sPort);
	}
	if (nPos == std::string::npos)
	{
		return;
	}
	strPageName = strTemp.substr(nPos, strTemp.size() - nPos);
	spdlog::info("using pageName:{}", strPageName.c_str());
}

// 关闭句柄
void CWininetHttp::Release()
{
	ReleaseHandle(m_hConnect);
	ReleaseHandle(m_hRequest);
	ReleaseHandle(m_hSession);
}

// 释放句柄
void CWininetHttp::ReleaseHandle(HINTERNET &hInternet)
{
	if (hInternet)
	{
		InternetCloseHandle(hInternet);
		hInternet = NULL;
	}
}

//UTF-8转为GBK2312
char* CWininetHttp::ConvertUTF2GBK(const char* utf8)
{
	int len = MultiByteToWideChar(CP_UTF8, 0, utf8, -1, NULL, 0);
	wchar_t* wstr = new wchar_t[len + 1];
	memset(wstr, 0, len + 1);
	MultiByteToWideChar(CP_UTF8, 0, utf8, -1, wstr, len);
	len = WideCharToMultiByte(CP_ACP, 0, wstr, -1, NULL, 0, NULL, NULL);
	char* str = new char[len + 1];
	memset(str, 0, len + 1);
	WideCharToMultiByte(CP_ACP, 0, wstr, -1, str, len, NULL, NULL);
	if (wstr) delete[] wstr;
	return str;
}