package leetcode;

import java.util.Stack;

public class RemoveAdjacentDuplicates {
//    public static void main(String[] args) {
//        new RemoveAdjacentDuplicates().process();
//    }

    private void process() {
        String str1 = "mississipie";
        System.out.println(removeDuplicates(str1));
        String str2 = "ocvvcolop";
        System.out.println(removeDuplicates(str2));
    }

    private String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.empty()) {
                stack.push(c);
            } else {
                if (c.equals(stack.peek())){
                } else {

                }
            }
        }
        char ch = ' ';
        return removeDuplicateUtils(s, ch);
    }

    private String removeDuplicateUtils(String s, char ch) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int i = 0;
        while (i < s.length()) {
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                int j = i;
                while (j + 1 < s.length() && s.charAt(j) == s.charAt(j + 1)) {
                    j++;
                }
                char lastChar = i > 0 ? s.charAt(i - 1) : ch;
                String remStr = removeDuplicateUtils(s.substring(j + 1, s.length()), lastChar);
                s = s.substring(0, i);
                int k = s.length(), l = 0;
                while (remStr.length() > 0 && s.length() > 0 && remStr.charAt(0) == s.charAt(s.length() - 1)) {
                    while (remStr.length() > 0 && remStr.charAt(0) != ch && remStr.charAt(0) == s
                            .charAt(s.length() - 1)) {
                        remStr = remStr.substring(1, remStr.length());
                    }
                    s = s.substring(0, s.length() - 1);
                }
                s = s + remStr;
                i = j;
            }
            else {
                i++;
            }
        }
        return s;
    }
    public static void main(String[] args) {
        String s = "SELECT O.id, A.id AS account_id,  O.created_at, O.updated_at, OPMV2.id AS opportunity_package_mapping_id,"
                + " OPMV2.rate_price, OPMV2.sales_price,TPA.current_slot_id, OPM.id AS opportunity_product_mapping_id"
                + " OPM.activation_parameters, P.total_amount, P.id AS payment_id, P.payment_received_date,"
                + " PA.extra_data FROM sapna.opportunities AS O"
                + " JOIN sapna.accounts AS A ON O.account_id = A.id"
                + " JOIN sapna.payments AS P on P.opportunity_id = O.id"
                + " JOIN sapna.termsheets AS TS on TS.opportunity_id = O.id"
                + " JOIN sapna.opportunity_package_mappings_v2 AS OPMV2 on OPMV2.opportunity_id = O.id "
                + " JOIN sapna.opportunity_product_mappings AS OPM  on OPM.opportunity_package_mapping_id = OPMV2.id "
                + " LEFT JOIN sapna.termsheet_product_activation AS TPA on TPA.termshhet_id = TS.id"
                + " LEFT JOIN sapna.product_activation AS PA on PA.id = TPA.termsheet_product_activation_id"
                + " where A.client_type_id = ?2 AND OPMV2.active=1 AND A.housing_profile_id = ?1 AND P.status_id = ?3"
                + " limit ?4 offset ?5";
        System.out.println(s.replaceAll("\n", ""));
    }
}
