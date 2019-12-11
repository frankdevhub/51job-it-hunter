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
}

//ͨ��HTTP����Get��Post��ʽ��ȡJSON��Ϣ
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
		//�ֲ�
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





	}
	catch (HttpInterfaceError error)
	{
		m_error = error;
	}
	return std::move(strRet);
}

// ����URL��ַ
void CWininetHttp::ParseURLWeb(std::string &lpUrl, std::string &strHostName
	, std::string &strPageName, WORD &sPort)
{
}

// �رվ��
void CWininetHttp::Release()
{
	ReleaseHandle(m_hConnect);
	ReleaseHandle(m_hRequest);
	ReleaseHandle(m_hSession);
}

// �ͷž��
void CWininetHttp::ReleaseHandle(HINTERNET &hInternet)
{
	if (hInternet)
	{
		InternetCloseHandle(hInternet);
		hInternet = NULL;
	}
}

//UTF-8תΪGBK2312
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