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
	Hir_InitErr, //��ʼ��ʧ��
	Hir_ConnectErr, //����HTTP������ʧ��
	Hir_SendErr, //��������ʧ��
	Hir_QueryErr, //��ѯHttp����ͷʧ��
	Hir_404, //ҳ�治����
	Hir_IllegalUrl, //��Ч��URL
	Hir_CreateFileErr, //�����ļ�ʧ��
	Hir_DownloadErr, //����ʧ��
	Hir_QueryIpErr, //��ȡ������Ӧ��ַʧ��
	Hir_SocketErr, //�׽��ִ���
	Hir_UserCancel, //�û�ȡ������
	Hir_BufferErr, //�ļ�̫�󻺳�������
	Hir_HeaderErr, //HTTP����ͷ����
	Hir_ParamErr, //�������󣬿�ָ�룬���ַ�
	Hir_UnkownErr, //δ֪����������
};

class CWininetHttp
{
public:
	CWininetHttp(void);
	~CWininetHttp(void);
};

