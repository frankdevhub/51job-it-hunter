#pragma once
#include <iostream>
#include <windows.h>
#include <wininet.h>

using namespace std;

//ÿ�ζ�ȡ���ֽ���
#define READ_BUFFER_SIZE 4096

enum HttpInterfaceError
{
	Hir_Success = 0, //�ɹ�
	HIr_InitErr, //��ʼ��ʧ��

};

class CWininetHttp
{
public:
	CWininetHttp(void);
	~CWininetHttp(void);
};

