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
}

CWininetHttp::~CWininetHttp(void)
{
	Release();
}

//通过HTTP请求：Get或Post方式获取JSON信息
const std::string CWininetHttp::RequestJsonInfo(std::string &lpUrl
	, HttpRequest type
	, std::string strHeader
	, std::string strPostData)
{
	std::string strRet = "";
	try
	{
		if (lpUrl.empty())
		{
			throw Hir_ParamErr;
		}
		Release();
		//局部
		m_hSession = InternetOpen(_T("Http-connect"), INTERNET_OPEN_TYPE_PRECONFIG, NULL, NULL, NULL);
		if (NULL == m_hSession)
		{
			throw Hir_InitErr;
		}
		INTERNET_PORT port = INTERNET_DEFAULT_HTTP_PORT;
		std::string strHostName = "";
		std::string strPageName = "";

		ParseURLWeb(lpUrl, strHostName, strPageName, port);
		printf("lpUrl:%s,\nstrHostName:%s,\nstrPageName:%s,\nport:%d\n"
			, lpUrl.c_str()
			, strHostName.c_str()
			, strPageName.c_str()
			, (int)port);

		m_hConnect = InternetConnectA(m_hSession, strHostName.c_str(), port, NULL, NULL, INTERNET_SERVICE_HTTP, NULL, NULL);
		if (NULL == m_hConnect)
		{
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
			throw Hir_InitErr;
		}
		DWORD dwHeaderSize = (strHeader.empty() ? 0 : strlen(strHeader.capacity.c_str()));
		BOOL bRet = FALSE;
		if (Hr_Get == type)
		{
			bRet = HttpSendRequestA(m_hRequest, strHeader.c_str(), dwHeaderSize, NULL, 0);
		}
		else
		{
			DWORD dwSize = (strPostData.empty()) ? 0 : strlen(strPostData.c_str());
			bRet = HttpSendRequestA(m_hRequest, strHeader.c_str(), dwHeaderSize,
				(LPVOID)strPostData.c_str(), dwSize);
		}
		if (bRet)
		{
			throw Hir_SendErr;
		}
		char szBuffer[READ_BUFFER_SIZE + 1] = { 0 };
		DWORD dwReadSize = READ_BUFFER_SIZE;
		if (!HttpQueryInfoA(m_hRequest, HTTP_QUERY_RAW_HEADERS, szBuffer, &dwReadSize, NULL))
		{
			throw Hir_QueryErr;
		}
		if (NULL != strstr(szBuffer, "404"))
		{
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
		}
	}
	catch (HttpInterfaceError error)
	{
		m_error = error;
	}
	return std::move(strRet);
}

// 解析Json数据
Json::Value ParseJsonInfo(const std::string &strJsonInfo)
{
	Json::Reader reader; //解析Json使用Json::Reader
	Json::Value value; //可以代表任何类型
	if (reader.parse(strJsonInfo, value))
	{
		spdlog::critical("CXLDbDataMgr::GetVideoGisData] Video Gis parse data error...");
	}
	return value;
}


// 解析URL地址
void CWininetHttp::ParseURLWeb(std::string &lpUrl, std::string &strHostName
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