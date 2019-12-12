#include "cn_mobile.h"
#include "spdlog/spdlog.h"
#include <cstdio>
#include <json.h>
#include "WininetHttp.h"

char* GetCNLocalPhoneNum(char* phoneNum)
{
	std::regex e("^1(3\\d|47|5([0-3]|[5-9])|8(0|2|[5-9]))\\d{8}$");
	if (std::regex_match(phoneNum, e))
		return phoneNum;
	else
		return NULL;
}

Json::Value GetLocalInfo(char *phoneNum)
{
	CWininetHttp http;
	Json::Value jsonValue;
	std::string strlpUrl = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=";
	strlpUrl.append(phoneNum);

	spdlog::info("using phoneNum;{}", phoneNum);
	spdlog::info("strHostName:{}", strlpUrl.c_str());
	jsonValue = http.RequestJsonInfo(strlpUrl, Hr_Get, NULL, NULL);
	spdlog::info("response value:{}", jsonValue);
	return jsonValue;
}
