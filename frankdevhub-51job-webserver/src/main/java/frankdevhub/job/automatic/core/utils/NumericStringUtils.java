package frankdevhub.job.automatic.core.utils;

@SuppressWarnings("all")
public class NumericStringUtils {
    private static final String[] units = {"千", "百", "十", "万"};
    private static final String[] bigUnits = {"万", "亿"};
    private static final char[] numChars = {'一', '二', '三', '四', '五', '六', '七', '八', '九' };
    private static char numZero = '零';

    public static int numberCN2Arab(String numberCN) {
        String tempNumberCN = numberCN;
        if (tempNumberCN == null) {
            return 0;
        }
        String[] nums = new String[bigUnits.length + 1];
        nums[0] = tempNumberCN;

        for (int i = (bigUnits.length - 1); i >= 0; i--) {
            int find = tempNumberCN.indexOf(bigUnits[i]);
            if (find != -1) {
                String[] tempStrs = tempNumberCN.split(bigUnits[i]);

                if (nums[0] != null) {
                    nums[0] = null;
                }
                if (tempStrs[0] != null) {
                    nums[i + 1] = tempStrs[0];
                }
                if (tempStrs.length > 1) {
                    tempNumberCN = tempStrs[1];
                    if (i == 0) {
                        nums[0] = tempStrs[1];
                    }
                } else {
                    tempNumberCN = null;
                    break;
                }
            }
        }

        String tempResultNum = "";
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != null) {
                tempResultNum += numberKCN2Arab(nums[i]);
            } else {
                tempResultNum += "0000";
            }
        }
        return Integer.parseInt(tempResultNum);
    }

    public static int numberCharCN2Arab(char onlyCNNumber) {

        if (numChars[0] == onlyCNNumber) {
            return 1;
        } else if (numChars[1] == onlyCNNumber || onlyCNNumber == '两') {
            return 2;
        } else if (numChars[2] == onlyCNNumber) {
            return 3;
        } else if (numChars[3] == onlyCNNumber) {
            return 4;
        } else if (numChars[4] == onlyCNNumber) {
            return 5;
        } else if (numChars[5] == onlyCNNumber) {
            return 6;
        } else if (numChars[6] == onlyCNNumber) {
            return 7;
        } else if (numChars[7] == onlyCNNumber) {
            return 8;
        } else if (numChars[8] == onlyCNNumber) {
            return 9;
        }

        return 0;
    }

    public static char numberCharArab2CN(char onlyArabNumber) {

        if (onlyArabNumber == '0') {
            return numZero;
        }
        if (onlyArabNumber > '0' && onlyArabNumber <= '9') {
            return numChars[onlyArabNumber - '0' - 1];
        }
        return onlyArabNumber;
    }

    public static String numberArab2CN(Integer num) {

        String tempNum = num + "";
        int numLen = tempNum.length();
        int start = 0;
        int end = 0;
        int per = 4;
        int total = (int) ((numLen + per - 1) / per);
        int inc = numLen % per;

        String[] numStrs = new String[total];

        for (int i = total - 1; i >= 0; i--) {
            start = (i - 1) * per + inc;
            if (start < 0) {
                start = 0;
            }
            end = i * per + inc;
            numStrs[i] = tempNum.substring(start, end);
        }

        String tempResultNum = "";
        int rempNumsLen = numStrs.length;

        for (int i = 0; i < rempNumsLen; i++) {
            if (i > 0 && Integer.parseInt(numStrs[i]) < 1000) {
                tempResultNum += numZero + numberKArab2CN(Integer.parseInt(numStrs[i]));
            } else {
                tempResultNum += numberKArab2CN(Integer.parseInt(numStrs[i]));
            }
            if (i < rempNumsLen - 1) {
                tempResultNum += bigUnits[rempNumsLen - i - 2];
            }
        }
        tempResultNum = tempResultNum.replaceAll(numZero + "$", "");
        return tempResultNum;

    }

    private static String numberKArab2CN(Integer num) {
        char[] numChars = (num + "").toCharArray();
        String tempStr = "";
        int inc = units.length - numChars.length;
        for (int i = 0; i < numChars.length; i++) {
            if (numChars[i] != '0') {
                tempStr += numberCharArab2CN(numChars[i]) + units[i + inc];
            } else {
                tempStr += numberCharArab2CN(numChars[i]);
            }
        }
        tempStr = tempStr.replaceAll(numZero + "+", numZero + "");
        tempStr = tempStr.replaceAll(numZero + "$", "");

        return tempStr;
    }

    private static String numberKCN2Arab(String numberCN) {
        if ("".equals(numberCN)) {
            return "";
        }
        int[] nums = new int[4];
        if (numberCN != null) {
            for (int i = 0; i < units.length; i++) {
                int idx = numberCN.indexOf(units[i]);
                if (idx > 0) {
                    char tempNumChar = numberCN.charAt(idx - 1);
                    int tempNumInt = numberCharCN2Arab(tempNumChar);
                    nums[i] = tempNumInt;
                }
            }
            char ones = numberCN.charAt(numberCN.length() - 1);
            nums[nums.length - 1] = numberCharCN2Arab(ones);
            if ((numberCN.length() == 2 || numberCN.length() == 1) && numberCN.charAt(0) == '十') {
                nums[nums.length - 2] = 1;
            }
        }
        String tempNum = "";
        for (int i = 0; i < nums.length; i++) {
            tempNum += nums[i];
        }
        return (tempNum);
    }

}
