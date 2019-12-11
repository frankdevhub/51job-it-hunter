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
	Hir_InitErr, //初始化失败
	Hir_ConnectErr, //连接HTTP服务器失败
	Hir_SendErr, //发送请求失败
	Hir_QueryErr, //查询Http请求头失败
	Hir_404, //页面不存在
	Hir_IllegalUrl, //无效的URL
	Hir_CreateFileErr, //创建文件失败
	Hir_DownloadErr, //下载失败
	Hir_QueryIpErr, //获取域名对应地址失败
	Hir_SocketErr, //套接字错误
	Hir_UserCancel, //用户取消下载
	Hir_BufferErr, //文件太大缓冲区不足
	Hir_HeaderErr, //HTTP请求头错误
	Hir_ParamErr, //参数错误，空指针，空字符
	Hir_UnkownErr, //未知的其他错误
};

enum HttpRequest
{
	Hr_Get,
	Hr_Post
};

class CWininetHttp
{
public:
	CWininetHttp(void);
	~CWininetHttp(void);

public:
	//通过HTTP请求：Get或Post方法获取JSON信息
	const::std::string RequestJsonInfo(std::string& lpUrl
		, HttpRequest type = Hr_Get
		, std::string lpHeader = ""
		, std::string lpPostData = "");

protected:
	//解析卡口JSON数据
	void ParseJson(const std::string &strJsonInfo);
	//关闭句柄
	void Release();
	//释放句柄
	void ReleaseHandle(HINTERNET &hInternet);
	//解析URL地址
	void ParseURLWeb(std::string &lpUrl, std::string &strHostName
		, std::string &strPageName, WORD &sPort);
	//UTF-8转GBK2312
	char* ConvertUTF2GBK(const char* utf8);

private:
	HINTERNET m_hSession;
	HINTERNET m_hConnect;
	HINTERNET m_hRequest;
	HttpInterfaceError m_error;

};

