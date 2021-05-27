package com.tianye.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.tianye.community.community.dto.AccessTokenDTO;
import com.tianye.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component   //紧紧的将当前类初始化到Spring容器的上下文                  //@Controller 将当前类作为路由API的一个承载者
//Component注释的类，自动实例化放到池子里面，当我们用的时候，可以直接拿出来用
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        // okHttp Post to a Server
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            System.out.println(string.split("&")[0].split("=")[1]);
            //string 示例  access_token=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&scope=user&token_type=bearer
            return string.split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public GithubUser getUser(String accessToken) {
        // get a URL
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;

    }
}
