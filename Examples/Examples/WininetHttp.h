#pragma once
#include <iostream>
#include <windows.h>
#include <wininet.h>

using namespace std;

//每次读取的字节数
#define READ_BUFFER_SIZE 4096

enum HttpInterfaceError
{
	Hir_Success = 0, //成功

};

class CWininetHttp
{
public:
	CWininetHttp(void);
	~CWininetHttp(void);
};

