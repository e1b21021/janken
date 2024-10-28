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
        return "redirect:/janken"; // じゃんけん画面にリダイレクト
    }

    // じゃんけん対戦の処理
    @GetMapping("/janken")
    public String janken(@RequestParam(required = false) String hand, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // ログインしているユーザー名を取得

        // 認証情報が存在しない場合、ログインページにリダイレクト
        if (username == null || "anonymousUser".equals(username)) {
            return "redirect:/login";
        }

        if (hand != null && !hand.isEmpty()) {
            String cpuHand = Janken.getCpuHand(); // CPUの手を取得 (最初はグー固定)
            String result = Janken.judge(hand, cpuHand);

            model.addAttribute("userHand", hand);
            model.addAttribute("cpuHand", cpuHand);
            model.addAttribute("result", result);
        }

        model.addAttribute("username", username);
        return "janken";
    }
}
