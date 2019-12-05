#include "cn_mobile.h"

char* GetCNLocalPhoneNum(char* phoneNum) {
	std::regex e("^1(3\\d|47|5([0-3]|[5-9])|8(0|2|[5-9]))\\d{8}$");
	if (std::regex_match(phoneNum, e))
		return phoneNum;
	else
		return NULL;
}

