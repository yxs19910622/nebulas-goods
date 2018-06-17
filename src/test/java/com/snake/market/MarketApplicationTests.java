package com.snake.market;

import io.nebulas.client.NebulasClient;
import io.nebulas.client.api.request.CallRequest;
import io.nebulas.client.api.request.Contract;
import io.nebulas.client.api.request.GetAccountStateRequest;
import io.nebulas.client.api.request.SendRawTransactionRequest;
import io.nebulas.client.api.response.*;
import io.nebulas.client.impl.HttpNebulasClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketApplicationTests {

	private static String wallet = "n1ZaXvhyjmvHT2SvbWRDnaXd9TdAJbYzNU5";
	private static String transaction = "n1yqmzU2pkJRp83KMz9Vm84wned6jTvNojj";

	private NebulasClient nebulasClient = HttpNebulasClient.create("https://testnet.nebulas.io");

	@Test
	public void testGetNebState() {
		Response<NebState> response = nebulasClient.getNebState();
		System.out.println(response);
	}

	@Test
	public void testGetAccountState() {
		Response<AccountState> response = nebulasClient.getAccountState(new GetAccountStateRequest(wallet));
		System.out.println(response);
	}

	@Test
	public void testCall() {
		Response<CallResult> response =
				nebulasClient.call(new CallRequest(wallet, transaction, "10000", 3l, "1000000", "2000000", new Contract().setFunction("getGoods").setArgs("[\"2\"]")));
//				nebulasClient.call(new CallRequest(wallet, transaction, "10000", 3l, "1000000", "2000000", new Contract().setFunction("addGoods").setArgs("[\"测试商品02\",\"测试数据02\"]")));
		System.out.println(response);
	}

	@Test
	public void testSendRawTransaction() {
		Response<RawTransaction> response = nebulasClient.sendRawTransaction(new SendRawTransactionRequest().setData("CiCrHtxyyIJks2/RErvBBA862D6iwAaGQ9OK1NisSGAuTBIYGiY1R9Fnx0z0uPkWbPokTeBIHFFKRaosGhgzPLPtjEF5cYRTgu3jz2egqWJwwF/i9wAiEAAAAAAAAAAADeC2s6dkAAAoAjDd/5jSBToICgZiaW5hcnlAZEoQAAAAAAAAAAAAAAAAAA9CQFIQAAAAAAAAAAAAAAAAAABOIFgBYkGLnnvGZEDSlocc202ZRWtUlbl2RHfGNdBY5eajFiHKThfgXIwGixh17LpnZGnYHlmfiGe2zqnFHdj7G8b2XIP2AQ=="));
		System.out.println(response);
	}



}
