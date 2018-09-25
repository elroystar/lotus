package com.lotus.lotusapp.utils;

/**
 * 校验密码工具类
 */
public class PasswordRuleUtil {

    /**
     * 校验密码
     * @param rule
     * @param password
     * @return
     */
    public static boolean checkPassword(String rule, String password) {
        try {
            // 密码规则转换int数组
            int[] arr = new int[rule.length()];
            for (int i = 0; i < rule.length(); i++) {
                arr[i] = Integer.parseInt(rule.substring(i, i + 1));
            }
            // 根据奇偶数获取三次校验规则
            int one = (arr[0] & 1) == 1 ? -arr[1] : arr[1];
            int two = (arr[2] & 1) == 1 ? -arr[3] : arr[3];
            int three = (arr[4] & 1) == 1 ? -arr[5] : arr[5];
            // 密码转换string数组
            String[] passwordArr = password.substring(password.indexOf("00")).split("00");
            // 校验第一次规则
            arr = new int[passwordArr[1].length()];
            if (arr.length > 0) {
                for (int i = 0; i < passwordArr[1].length(); i++) {
                    arr[i] = Integer.parseInt(passwordArr[1].substring(i, i + 1));
                }
                Boolean b = false;
                int x = 0;
                int y = 0;
                CheckRule checkRule = new CheckRule(arr, b, x, y).invoke();
                x = checkRule.getX();
                y = checkRule.getY();
                if (Math.abs(x + one) != y) {
                    return false;
                }
            } else {
                return false;
            }
            // 校验第二次规则
            arr = new int[passwordArr[2].length()];
            if (arr.length > 0) {
                for (int i = 0; i < passwordArr[2].length(); i++) {
                    arr[i] = Integer.parseInt(passwordArr[2].substring(i, i + 1));
                }
                Boolean b = false;
                int x = 0;
                int y = 0;
                CheckRule checkRule = new CheckRule(arr, b, x, y).invoke();
                x = checkRule.getX();
                y = checkRule.getY();
                if (Math.abs(x + two) != y) {
                    return false;
                }
            } else {
                return false;
            }
            // 校验第三次规则
            arr = new int[passwordArr[3].length()];
            if (arr.length > 0) {
                for (int i = 0; i < passwordArr[3].length(); i++) {
                    arr[i] = Integer.parseInt(passwordArr[3].substring(i, i + 1));
                }
                Boolean b = false;
                int x = 0;
                int y = 0;
                CheckRule checkRule = new CheckRule(arr, b, x, y).invoke();
                x = checkRule.getX();
                y = checkRule.getY();
                if (Math.abs(x + three) != y) {
                    return false;
                }
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * 校验密码规则
     */
    private static class CheckRule {
        private int[] arr;
        private Boolean b;
        private int x;
        private int y;

        public CheckRule(int[] arr, Boolean b, int x, int y) {
            this.arr = arr;
            this.b = b;
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public CheckRule invoke() {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    x = arr[i];
                    y = arr[i + 1];
                    b = true;
                }
                if (b) {
                    break;
                }
            }
            return this;
        }
    }
}
