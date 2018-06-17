package com.snake.market.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.snake.market.model.Goods;
import io.nebulas.client.NebulasClient;
import io.nebulas.client.api.request.CallRequest;
import io.nebulas.client.api.request.Contract;
import io.nebulas.client.api.response.CallResult;
import io.nebulas.client.api.response.Response;
import io.nebulas.client.impl.HttpNebulasClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxs on 2018/6/16.
 */
@Service
public class NebulasService {

    private static String wallet = "n1ZaXvhyjmvHT2SvbWRDnaXd9TdAJbYzNU5";
    private static String transaction_1 = "n1g7mtjecxtQTT8HHxY1fcrTgkaXEh6Pkb9";

    private static NebulasClient nebulasClient = HttpNebulasClient.create("https://testnet.nebulas.io");
    private static Gson gson = new Gson();

    public void addGoods(String title, String url, String label, String message) {

        List<String> list = new ArrayList<>();
        list.add(title);
        list.add(message);
        list.add(url);
        list.add(label);
        String str = call(transaction_1, "addGoods", list);
        System.out.println(str);
    }

    public Goods getGoods(String id) {
        List<String> list = new ArrayList<>();
        list.add(id);
        String str = call(transaction_1, "getGoods", list);
        return gson.fromJson(str, Goods.class);
    }

    public List<Goods> getGoodsList() {
        String str = call(transaction_1, "getGoodsList", null);
        List<Goods> list = gson.fromJson(str, new TypeToken<List<Goods>>() {
        }.getType());
        return list;
    }

    private static String call(String transaction, String function, Object object) {

        String args = gson.toJson(object).replaceAll("}", "]").replaceAll("\\{", "[");

        Response<CallResult> response =
                nebulasClient.call(new CallRequest(wallet, transaction, "10000", 3l, "1000000",
                        "2000000", new Contract().setFunction(function).setArgs(args)));

        CallResult result = response.getResult();
        return result.getResult();
    }

}
