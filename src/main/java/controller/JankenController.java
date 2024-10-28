package oit.is.z2028.kaizi.janken.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z2028.kaizi.janken.model.Janken;

@Controller
public class JankenController {

    // ユーザー名を入力してじゃんけん画面に遷移する処理
    @PostMapping("/enter")
    public String enter(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "janken";
    }

    // じゃんけん対戦の処理
    @GetMapping("/janken")
    public String janken(@RequestParam String hand, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // ログインしているユーザー名を取得

        String cpuHand = Janken.getCpuHand(); // CPUの手を取得 (最初はグー固定)
        String result = Janken.judge(hand, cpuHand);

        model.addAttribute("username", username);
        model.addAttribute("userHand", hand);
        model.addAttribute("cpuHand", cpuHand);
        model.addAttribute("result", result);
        return "janken";
    }
}
