#include "../Common/cn_mobile.h"
#include <iostream>
#include <string.h>
#include <stdio.h>
#include <json.h>

void printRes(char* res)
{
	if (NULL == res)
		printf("ERROR FORMAT:[%s]", res);
	else
		printf("CORRENT:[%s]", res);

	printf("\n");
	res = NULL;
}
//获取手机号码归属地
int main()
{
	printf("start");
	char* phoneNum = (char*)"13585844052";
	Json::Value value = ::GetLocalInfo(phoneNum);
}

//校验手机号码格式
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
