#include "WininetHttp.h"
#include <json.h>
#include <fstream>
#pragma comment(lib,"Wininet.lib")
#include <tchar.h>
using namespace std;

CWininetHttp::CWininetHttp(void) :m_hSession(NULL), m_hConnect(NULL), m_hRequest(NULL)
{
}

CWininetHttp::~CWininetHttp(void)
{
	Release();
}