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

void CWininetHttp::Release()
{
	
}