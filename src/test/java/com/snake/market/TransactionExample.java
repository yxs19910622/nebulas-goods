package com.snake.market;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.nebulas.account.AccountManager;
import io.nebulas.client.NebulasClient;
import io.nebulas.client.api.request.GetAccountStateRequest;
import io.nebulas.client.api.request.SendRawTransactionRequest;
import io.nebulas.client.api.response.AccountState;
import io.nebulas.client.api.response.NebState;
import io.nebulas.client.api.response.RawTransaction;
import io.nebulas.client.api.response.Response;
import io.nebulas.client.impl.HttpNebulasClient;
import io.nebulas.core.*;
import io.nebulas.util.ByteUtils;
import rpcpb.ApiServiceGrpc;
import rpcpb.Rpc;

import java.math.BigInteger;

public class TransactionExample {

    private static byte[] passphrase = "n1ZaXvhyjmvHT2SvbWRDnaXd9TdAJbYzNU5".getBytes();
    private static NebulasClient nebulasClient = HttpNebulasClient.create("https://testnet.nebulas.io");
    private static String wallet = "n1ZaXvhyjmvHT2SvbWRDnaXd9TdAJbYzNU5";
    public static void main(String args[]) throws Exception {
        AccountManager manager = new AccountManager();
        NebState nebState = nebulasClient.getNebState().getResult();
        Response<AccountState> response = nebulasClient.getAccountState(new GetAccountStateRequest(wallet));
        System.out.println(response);
        // binary tx
        int chainID = nebState.getChainId(); //1 mainet,1001 testnet, 100 default private
        Address from = manager.newAccount(passphrase);
        //Address to = manager.newAccount("topassphrase".getBytes());
        Address to = Address.ParseFromString("n1g6JZsQS1uRUySdwvuFJ7FYT4dFoyoSN5q");
        BigInteger value = new BigInteger("0");
        long nonce = 1; // nonce = from.nonce + 1
        Transaction.PayloadType payloadType = Transaction.PayloadType.BINARY;
        byte[] payload = new TransactionBinaryPayload(null).toBytes();
        BigInteger gasPrice = new BigInteger("1000000"); // 0 < gasPrice < 10^12
        BigInteger gasLimit = new BigInteger("20000"); // 20000 < gasPrice < 50*10^9
//        Transaction tx = new Transaction(chainID, from, to, value, nonce, payloadType, payload, gasPrice, gasLimit);
//        manager.signTransaction(tx,passphrase);
//        byte[] rawData = tx.toProto();
        // senrawTransaction with @rawData
        // https://github.com/nebulasio/wiki/blob/master/rpc.md#sendrawtransaction
//        SendRawTransaction(rawData);

        // deploy tx
//        payloadType = Transaction.PayloadType.DEPLOY;
//        payload = new TransactionDeployPayload("js", "var demo = 1;", "").toBytes();
//        // deploy from == to
//        tx = new Transaction(chainID, from, from, value, nonce, payloadType, payload, gasPrice, gasLimit);
//        manager.signTransaction(tx,passphrase);
//        rawData = tx.toProto();
//        // senrawTransaction with @rawData
//        // https://github.com/nebulasio/wiki/blob/master/rpc.md#sendrawtransaction
//        SendRawTransaction(rawData);

        // call tx
        payloadType = Transaction.PayloadType.CALL;
        payload = new TransactionCallPayload("addGoods", "[\"1\",\"2\",\"3\",\"4\",]").toBytes();
        // call to = contract address
//        from = Address.ParseFromString("n1ZaXvhyjmvHT2SvbWRDnaXd9TdAJbYzNU5");
        to = Address.ParseFromString("n1g7mtjecxtQTT8HHxY1fcrTgkaXEh6Pkb9");
        Transaction tx = new Transaction(chainID, from, to, value, nonce, payloadType, payload, gasPrice, gasLimit);
        manager.signTransaction(tx,passphrase);
        byte[] rawData = tx.toProto();
        // senrawTransaction with @rawData
        // https://github.com/nebulasio/wiki/blob/master/rpc.md#sendrawtransaction
        SendRawTransaction(rawData);
    }

    private static void SendRawTransaction(byte[] data) throws Exception {
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("https://testnet.nebulas.io", 8684).usePlaintext().build();
//        Rpc.SendRawTransactionRequest request = Rpc.SendRawTransactionRequest
//                .newBuilder()
//                .setData(ByteString.copyFrom(data))
//                .build();
//
//        ApiServiceGrpc.ApiServiceBlockingStub apiServiceStub = ApiServiceGrpc.newBlockingStub(channel);
//        Rpc.SendTransactionResponse response = apiServiceStub.sendRawTransaction(request);
//        System.out.println(response);


        Response<RawTransaction> rawTransactionResponse = nebulasClient.sendRawTransaction(new SendRawTransactionRequest(ByteUtils.Base64ToString(data)));
        System.out.println(rawTransactionResponse);

//        ApiServiceGrpc.ApiServiceStub serviceStub = ApiServiceGrpc.newStub(channel);
//        final CountDownLatch finishLatch = new CountDownLatch(1);
//        StreamObserver<Rpc.SendTransactionResponse> observer = new StreamObserver<Rpc.SendTransactionResponse>() {
//            @Override
//            public void onNext(Rpc.SendTransactionResponse value) {
//                System.out.println(value);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                finishLatch.countDown();
//            }
//
//            @Override
//            public void onCompleted() {
//                finishLatch.countDown();
//            }
//        };
//
//        serviceStub.sendRawTransaction(request,observer);
    }
}