
var NebPay = require("nebpay");
var nebPay = new NebPay();

var chainnetConfig = {
    mainnet: {
        name: "主网",
        contractAddress: "n1sRJc54JJzmpabguzz1uodtyR2cECHUXde",
        host: "https://mainnet.nebulas.io",
        payhost: "https://pay.nebulas.io/api/mainnet/pay"
    },
    testnet: {
        name: "测试网",
        contractAddress: "n1g7mtjecxtQTT8HHxY1fcrTgkaXEh6Pkb9",
        host: "https://testnet.nebulas.io",
        payhost: "https://pay.nebulas.io/api/pay"
    }
};

var chainInfo = chainnetConfig["testnet"];
var HttpRequest = require("nebulas").HttpRequest;
var Neb = require("nebulas").Neb;
var myneb = new Neb();
myneb.setRequest(new HttpRequest(chainInfo.host));
var nasApi = myneb.api;
var dappAddress = chainInfo.contractAddress;


function getAccountname(){
    var to = dappAddress;
    var value = "0";
    var callFunction = "searchAccount";
    var callArgs = "";
    nebPay.simulateCall(to, value, callFunction, callArgs, {
        listener: getAccountnameListener
    });
}


function addGoods (){

    var imgUrl = $("#imgUrl").val();
    var title = $("#title").val();
    var label = $("#label").val();
    var message = $("#message").val();

    var to = dappAddress;
    var value = "0";
    var callFunction = "addGoods";
    var callArgs = "[\""+title+"\",\""+message+"\",\""+imgUrl+"\",\""+label+"\"]";

    //监听
    nebPay.call(to, value, callFunction, callArgs, {
        listener: function(resp){
            console.log("test listenr "+resp.txhash);
            if(resp == "Error: Transaction rejected by user"){
                alert("上传取消");
                return false;
            }else{
                alert("已提交区块链网络，请等待写入区块链！");
                checkPayStatus(resp.txhash);
            }
        }
    });
}

function checkPayStatus(txhash) {
    console.log("checkpaystatas "+txhash);
    $(".account_detail").hide();
    $(".init_account").hide();
    $(".service_wrapper").hide();
    $(".upload_data").hide();
    $(".loading_div").show();
    var timerId = setInterval(function(){
        nasApi.getTransactionReceipt({
            hash:txhash
        }).then(function(receipt){
            console.log("checkPayStatus");
            if(receipt.status == 1){
                clearInterval(timerId);
                var res = receipt.execute_result;
                console.log("test success return="+res);
                getAccountname();

            }else if(receipt.status == 0){
                clearInterval(timerId);
                console.log("test fail err="+receipt.execute_error);
                alert("失败，请再次尝试！");

                getAccountname();
            }
        }).catch(function(err){
            console.log("test error");
        });
    },3*1000);
}