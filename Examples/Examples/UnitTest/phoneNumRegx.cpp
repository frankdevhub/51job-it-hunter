//#include "stdafx.h"
#include "../Common/cn_mobile.h"
#include <iostream>
#include <string.h>
#include <stdio.h>
#include <json.h>
#include "spdlog/spdlog.h"

void printRes(char* res)
{
	if (NULL == res)
		spdlog::info("ERROR FORMAT:[%s]", res);
	else
		spdlog::info("CORRENT:[%s]", res);
	res = NULL;
}
//��ȡ�ֻ����������
int main()
{
	char* phoneNum = (char*)"13585844052";
	::GetLocalInfo(phoneNum);
	system("pause");
}

//У���ֻ������ʽ
//int main()
//{
//	printf("start");
//	char* res;
//	char *phone = (char*)"13585844052";
//	res = ::GetCNLocalPhoneNum(phone);
//	printRes(res);
//
//	phone = (char*)"23583374052";
//	res = ::GetCNLocalPhoneNum(phone);
//	printRes(res);
//
//	phone = (char*)"10444433978";
//	res = ::GetCNLocalPhoneNum(phone);
//	printRes(res);
//
//	system("pause");
//}
