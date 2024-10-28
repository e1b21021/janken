package oit.is.z2028.kaizi.janken.model;

import java.util.Random;

public class Janken {

    // CPUの手を決定 (最初は固定でグー)
    public static String getCpuHand() {
        return "グー";
    }

    // 余力があればランダムな手を出すように変更
    public static String getRandomCpuHand() {
        String[] hands = {"グー", "チョキ", "パー"};
        Random rand = new Random();
        return hands[rand.nextInt(hands.length)];
    }

    // じゃんけんの結果を判定する
    public static String judge(String userHand, String cpuHand) {
        if (userHand.equals(cpuHand)) {
            return "引き分け";
        } else if ((userHand.equals("グー") && cpuHand.equals("チョキ")) ||
                   (userHand.equals("チョキ") && cpuHand.equals("パー")) ||
                   (userHand.equals("パー") && cpuHand.equals("グー"))) {
            return "勝ち";
        } else {
            return "負け";
        }
    }
}