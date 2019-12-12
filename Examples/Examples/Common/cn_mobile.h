#ifndef _CN_MOBILE_H_
#define _CN_MOBILE_H_
#include <iostream>
#include <string>
#include <regex>
#include <json.h>

char* GetCNLocalPhoneNum(char* phoneNum);
Json::Value GetLocalInfo(char *phoneNum);

#endif;