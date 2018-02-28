package erik.jdbc;

import java.util.*;

/**
 * Created by nali on 2017/10/20.
 */
public class GenerateSql {


    public static String subAccountIds = "287918533\n" +
            "317222935\n" +
            "302625192\n" +
            "317810432\n" +
            "298960083\n" +
            "299526314\n" +
            "289636261\n" +
            "294959405\n" +
            "317031065\n" +
            "330462711\n" +
            "331854252\n" +
            "319113865\n" +
            "320352839\n" +
            "313414702\n" +
            "304211761\n" +
            "317745942\n" +
            "289279414\n" +
            "301779886\n" +
            "289215022\n" +
            "318460028\n" +
            "311853969\n" +
            "288458469\n" +
            "334153057\n" +
            "300240377\n" +
            "291675686\n" +
            "289332785\n" +
            "330920493\n" +
            "291439569\n" +
            "332975132\n" +
            "335670826\n" +
            "301540098\n" +
            "312151600\n" +
            "294810455\n" +
            "312610693\n" +
            "319596745\n" +
            "302479648\n" +
            "299578272\n" +
            "297543141\n" +
            "303211272\n" +
            "294910775\n" +
            "295039158\n" +
            "296991070\n" +
            "300349342\n" +
            "291229706\n" +
            "309658900\n" +
            "317241724\n" +
            "295063839\n" +
            "306066691\n" +
            "291145346\n" +
            "291412370\n" +
            "305751989\n" +
            "301170934\n" +
            "305120213\n" +
            "311843866\n" +
            "291019076\n" +
            "292560594\n" +
            "310746845";

    public static String userIds = "44455006\n" +
            "46828800\n" +
            "91384236\n" +
            "92985808\n" +
            "82705055\n" +
            "13333149\n" +
            "86940604\n" +
            "1381386\n" +
            "92899649\n" +
            "85804999\n" +
            "94427765\n" +
            "91682892\n" +
            "93267652\n" +
            "92457459\n" +
            "62666357\n" +
            "55842825\n" +
            "89291156\n" +
            "58172127\n" +
            "12889185\n" +
            "68107189\n" +
            "32695138\n" +
            "43988082\n" +
            "94590509\n" +
            "85605221\n" +
            "2212383\n" +
            "24995898\n" +
            "94402949\n" +
            "31864921\n" +
            "94629665\n" +
            "88986511\n" +
            "85883499\n" +
            "27239909\n" +
            "90830675\n" +
            "45122290\n" +
            "93168933\n" +
            "49162359\n" +
            "85926003\n" +
            "80821181\n" +
            "79186642\n" +
            "40572610\n" +
            "82089441\n" +
            "90988327\n" +
            "86505822\n" +
            "78944898\n" +
            "21634472\n" +
            "64926462\n" +
            "88505160\n" +
            "80224533\n" +
            "18706298\n" +
            "82017327\n" +
            "91687440\n" +
            "86510583\n" +
            "87940254\n" +
            "78426422\n" +
            "85465912\n" +
            "11824628\n" +
            "51947586";

    public static void main(String[] args) {


        String[] subAccountIdArray = subAccountIds.split("\\n");
        String[] userIdArray = userIds.split("\\n");

        System.out.println("subAccountIdArray.length=" + subAccountIdArray.length);
        System.out.println("userIdArray.length=" + userIdArray.length);

        Map<String, String> tableNameToSubAccountIdMap = new HashMap<>();

        List<TwoTupleTableNameAndSubAccountId> twoTuple=new LinkedList<>();

        for (int i = 0; i < subAccountIdArray.length; i++) {


            Integer userIdInt = Integer.parseInt(userIdArray[i]);
            String tableName = "XIMA_XAC_1.XAC_SUB_ACCOUNT_";
            StringBuilder tableNameSuffix = new StringBuilder();
            if (userIdInt % 100 / 10 == 0) {
                tableNameSuffix.append(0);
            }
            tableNameSuffix.append(userIdInt % 100);

            if(tableNameToSubAccountIdMap.containsKey(tableName+tableNameSuffix.toString())){
                twoTuple.add(new TwoTupleTableNameAndSubAccountId(tableName+tableNameSuffix,tableNameToSubAccountIdMap.get(tableName+tableNameSuffix.toString())));
            }
            tableNameToSubAccountIdMap.put(tableName + tableNameSuffix.toString(), subAccountIdArray[i]);

        }

        System.out.println("subAccountIdArray.length=" + subAccountIdArray.length);
        System.out.println("tableNameToSubAccountIdMap.size()=" + tableNameToSubAccountIdMap.size());
        System.out.println("twoTuple.size()=" + twoTuple.size());
        tableNameToSubAccountIdMap.entrySet().stream().sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey())).forEach(entry-> System.out.println(generate(entry.getKey(),entry.getValue())));
        System.out.println("---------------");

        Collections.sort(twoTuple, new Comparator<TwoTupleTableNameAndSubAccountId>() {
            @Override
            public int compare(TwoTupleTableNameAndSubAccountId tuple1, TwoTupleTableNameAndSubAccountId tuple2) {
                return tuple1.getSubAccountId().compareTo(tuple2.getSubAccountId());
            }
        });
        for(int i=0;i<twoTuple.size();i++){
            System.out.printf("%s\n",generate(twoTuple.get(i).getTableName(),twoTuple.get(i).getSubAccountId()));
        }



    }

    public static String generate(String tableName, String subAccountId) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("UPDATE ")
                .append(tableName)
                .append(" SET BALANCE_AMOUNT=CEIL(BALANCE_AMOUNT) WHERE SUB_ACCOUNT_ID=")
                .append(subAccountId)
                .append(";");


        return stringBuffer.toString();
    }

    public static class TwoTupleTableNameAndSubAccountId implements Comparable{

        private String tableName;
        private String subAccountId;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getSubAccountId() {
            return subAccountId;
        }

        public void setSubAccountId(String subAccountId) {
            this.subAccountId = subAccountId;
        }

        public TwoTupleTableNameAndSubAccountId(String tableName, String subAccountId) {
            this.tableName = tableName;
            this.subAccountId = subAccountId;
        }

        @Override
        public String toString() {
            return "TwoTupleTableNameAndSubAccountId{" +
                    "tableName='" + tableName + '\'' +
                    ", subAccountId='" + subAccountId + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Object o) {

            return tableName.compareTo(((TwoTupleTableNameAndSubAccountId)o).getTableName());

        }
    }

}
