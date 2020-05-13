package DailyInterviewPro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BearsCodeChef {

    static List question = new ArrayList<Character>(Arrays.asList('B', 'G', 'P'));


    static int calculateWays(char[][] bears) {
        int sum=0;
        int values[][]= new int[bears.length][bears.length];
        for (int i = 0; i < bears.length; i++) {

            for (int j = 0; j < bears.length; j++) {
                if (bears[i][j] == 'G') {
                    if (!noGrizlyBearTogetherConditionVerify(bears, i, j)) {
                        return 0;
                    }
                } else if (bears[i][j] == 'P' || bears[i][j] == 'B') {
                    if (!verifyNoPolarAndBrownBearTogether(bears, i, j)) {
                        return 0;
                    }else{
                        values[i][j]=calculateValues(bears,i,j,bears[i][j]);
                    }
                } else if (bears[i][j] == '?') {
                    values[i][j] = verifyforQuestionMark(bears, i, j);
                    if (values[i][j] == -1) {
                        return 0;
                    }
                } else{
                    values[i][j]=-1;
                }
            }

        }

        int ways=1;
        for(int i=0; i<bears.length;i++){
            for(int j=0;j<bears.length;j++){
                System.out.print(values[i][j]+ " ");
                ways=(ways*values[i][j])%1000000007;
            }
            System.out.println();
        }

        return Math.abs(ways);
    }


    static boolean noGrizlyBearTogetherConditionVerify(char[][] bears, int i, int j) {
        if (bears[i][j] == 'G') {
            if (j + 1 < bears.length) {
                if (bears[i][j + 1] != '.') {
                    return false;
                }
            }

            if (j - 1 >= 0) {
                if (bears[i][j - 1] != '.') {
                    return false;
                }
            }

            if (i - 1 >= 0) {
                if (bears[i - 1][j] != '.') {
                    return false;
                }
            }
            if (i + 1 < bears.length) {
                if (bears[i + 1][j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean verifyNoPolarAndBrownBearTogether(char[][] bears, int i, int j) {
        if (bears[i][j] == 'P') {
            if (j + 1 < bears.length) {
                if (bears[i][j + 1] == 'B') {
                    return false;
                }
            }

            if (j - 1 >= 0) {
                if (bears[i][j - 1] == 'B') {
                    return false;
                }
            }

            if (i - 1 >= 0) {
                if (bears[i - 1][j] == 'B') {
                    return false;
                }
            }
            if (i + 1 < bears.length) {
                if (bears[i + 1][j] == 'B') {
                    return false;
                }
            }
        } else if (bears[i][j] == 'B') {
            if (j + 1 < bears.length) {
                if (bears[i][j + 1] == 'P') {
                    return false;
                }
            }

            if (j - 1 >= 0) {
                if (bears[i][j - 1] == 'P') {
                    return false;
                }
            }

            if (i - 1 >= 0) {
                if (bears[i - 1][j] == 'P') {
                    return false;
                }
            }
            if (i + 1 < bears.length) {
                if (bears[i + 1][j] == 'P') {
                    return false;
                }
            }

        }
        return true;
    }

    static int verifyforQuestionMark(char[][] bears, int i, int j) {
        boolean isB = false;
        boolean isG = false;
        boolean isP = false;
        boolean isDot = false;
        boolean isPosA=false;
        boolean isPosB=false;
        boolean isPosC=false;
        boolean isPosD=false;

        boolean isQuestmark = false;
        boolean isQuestmarkA = false;
        boolean isQuestmarkB = false;
        boolean isQuestmarkC = false;
        boolean isQuestmarkD = false;



        if (j + 1 < bears.length) {
            if (bears[i][j + 1] == 'B') {
                isB = true;
            }
            if (bears[i][j + 1] == 'P') {
                isP = true;
            }
            if (bears[i][j + 1] == 'G') {
                isG = true;
            }
            if (bears[i][j + 1] == '.') {
                isDot = true;
                isPosA=true;
            }
            if (bears[i][j + 1] == '?') {
                isQuestmark = true;
                isQuestmarkA=true;
            }
        }

        if (j - 1 >= 0) {
            if (bears[i][j - 1] == 'B') {
                isB = true;
            }
            if (bears[i][j - 1] == 'P') {
                isP = true;
            }
            if (bears[i][j - 1] == 'G') {
                isG = true;
            }
            if (bears[i][j - 1] == '.') {
                isDot = true;
                isPosB=true;

            }
            if (bears[i][j - 1] == '?') {
                isQuestmark = true;
                isQuestmarkB=true;

            }
        }

        if (i - 1 >= 0) {
            if (bears[i - 1][j] == 'B') {
                isB = true;
            }
            if (bears[i - 1][j] == 'P') {
                isP = true;
            }
            if (bears[i - 1][j] == 'G') {
                isG = true;
            }
            if (bears[i - 1][j] == '.') {
                isDot = true;
                isPosC=true;

            }
            if (bears[i - 1][j] == '?') {
                isQuestmark = true;
                isQuestmarkC=true;

            }

        }
        if (i + 1 < bears.length) {
            if (bears[i + 1][j] == 'B') {
                isB = true;
            }
            if (bears[i + 1][j] == 'P') {
                isP = true;
            }
            if (bears[i + 1][j] == 'G') {
                isG = true;
            }
            if (bears[i + 1][j] == '.') {
                isDot = true;
                isPosD=true;

            }
            if (bears[i + 1][j] == '?') {
                isQuestmark = true;
                isQuestmarkD=true;
            }
        }

        if (isB && isG && isP && isDot) {
            return -1;
        }
        if (isB && isG && isP && isQuestmark) {
            return -1;
        } else if ((isB && isG && isDot && isQuestmark) || (isB && isG && !isDot && isQuestmark) || (isB && isG && isDot && !isQuestmark)) {
            return -1;
        } else if ((isB && isP && isDot && isQuestmark) && (isB && isP && !isDot && isQuestmark) && (isB && isP && isDot && !isQuestmark)) {
            return -1;
        } else if ((isG && isP && isDot && isQuestmark) && (isG && isP && !isDot && isQuestmark) && (isG && isP && isDot && !isQuestmark)) {
            return -1;
        } else if ((isB && isQuestmark && !isDot) || (isB && isDot && !isQuestmark)) {
            return 1;
        } else if ((isP && isQuestmark && !isDot) || (isP && isDot && !isQuestmark)) {
            return 1;
        } else if ((isG && isQuestmark && !isDot) || (isG && isDot && !isQuestmark)) {
            return 1;
        } else if (isDot && isQuestmark) {
            return 1;
        } else if (isQuestmark) {
            return 1;
        } else if(isDot){
            return 3;
        }
        return 1;
    }

    static int calculateValues(char bear[][], int i, int j, char value) {
        if (bear[i][j] != '?' || bear[i][j] != '.' || bear[i][j] != value) {
            return 0;
        }

       /* if(bear[i][j] != value){
            if(bear[i][j] != '?' ||bear[i][j] != '.'  ){
                return 0;
            }
        }*/
        if (j + 1 < bear.length) {
            if(calculateValues(bear, i, j+1, value)==0){
                return 0;
            }
        }
        if (i + 1 < bear.length) {
            if(calculateValues(bear, i+1, j, value)==0){
                return 0;
            }
        }
        if (i - 1 >= 0) {
            if(calculateValues(bear, i-1, j, value)==0){
                return 0;
            }
        }
        if (j - 1 >= 0) {
            if(calculateValues(bear, i, j-1, value)==0){
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        char bears[][] = {{'?', '.', '?', '.', '?', '.', '?'},
                          {'.', '?', '.', '?', '.', '?', '.'},
                          {'?', '.', '?', '.', '?', '.', '?'},
                          {'.', '?', '.', '?', '.', '?', '.'},
                          {'?', '.', '?', '.', '?', '.', '?'},
                          {'.', '?', '.', '?', '.', '?', '.'},
                          {'?', '.', '?', '.', '?', '.', '?'}};

        char bear1[][]={{'.','.','?'},{'.','?','B'},{'G','.','.'}};
        char bear2[][]={{'?','.','.'},{'.','?','?'},{'?','?','.'}};
        char bear3[][]={{'G','G'},{'.','.'}};
        char bear4[][]={{'P','P'},{'P','P'}};
        char bear5[][]={{'?','?','P'},{'?','?','?'},{'?','?','B'}};



        //System.out.println(calculateWays(bears));
        //System.out.println(calculateWays(bear1));
       // System.out.println(calculateWays(bear2));
        //System.out.println(calculateWays(bear3));
        System.out.println(calculateWays(bear4));
        //System.out.println(calculateWays(bear5));



    }

}
