package com.tianye.community.community.controller;

import com.tianye.community.community.dto.AccessTokenDTO;
import com.tianye.community.community.dto.GithubUser;
import com.tianye.community.community.mapper.UserMapper;
import com.tianye.community.community.model.User;
import com.tianye.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setCode(code);   // code: 25f55dac89d8e301a0aa(每次都变)
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);  // 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
        System.out.println("accessToken: " + accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        System.out.println(githubUser.getName());
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();

            // 为对象的属性赋值
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());

            userMapper.insert(user);    // 将user的信息写入到database
            // 登录成功将 token写入cookie里面
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            // 登录失败  重新登录
            return "redirect:/";
        }
    }
}
