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

// ¹Ø±Õ¾ä±ú
void CWininetHttp::Release()
{
	ReleaseHandle(m_hConnect);
	ReleaseHandle(m_hRequest);
	ReleaseHandle(m_hSession);
}

// ÊÍ·Å¾ä±ú
void CWininetHttp::ReleaseHandle(HINTERNET &hInternet)
{
	if (hInternet)
	{
		InternetCloseHandle(hInternet);
		hInternet = NULL;
	}
}