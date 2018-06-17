/**
 * Created by yangxs on 2018/6/14.
 */
var Goods = function (jsonString) {
    if (jsonString) {
        var obj = JSON.parse(jsonString);
        this.id = obj.id;
        this.title = obj.title;
        this.content = obj.content;
        this.url = obj.url;
        this.label = obj.label;
    } else {
        this.id = "";
        this.title = "";
        this.content = "";
        this.url = "";
        this.label = "";
    }
};

Goods.prototype = {
    toString: function () {
        return JSON.stringify(this);
    }
};

var TheGoods = function () {
    LocalContractStorage.defineProperty(this, "goodsId");
    LocalContractStorage.defineMapProperty(this, "goodsList");
};

TheGoods.prototype = {
    init: function () {
        this.goodsId = 0;
        var goodsList = [
            {
                title: "MARSHALL MAJOR II 蓝牙耳机",
                content: "MARSHALL MAJOR II蓝牙耳机采用apt-X高保真传输技术，音质十分有保证，用它听摇滚真是high爆了！",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/9415a2feab024165a8e0ce6dcf8f2c321528978053079.jpg@768w_1l",
                label: "耳机"
            },
            {
                title: "可口可乐北极熊唇彩",
                content: "你亲吻过可乐味的女孩么？",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/c6eeabfa552b407c90de1ab4606017111528980289989.jpg@768w_1l",
                label: "唇彩"
            }
        ];

        for (var i = 0; i < goodsList.length; i++) {
            var goods = goodsList[i];
            this.addGoods(goods.title, goods.content, goods.url, goods.label)
        }
    },
    addGoods: function (title, content, url, label) {
        var goods = new Goods(JSON.stringify({
            id: this.goodsId,
            title: title,
            content: content,
            url: url,
            label: label
        }));

        this.goodsList.set(this.goodsId, goods);
        this.goodsId += 1;
    },

    getGoods: function (goodsId) {
        return this.goodsList.get(goodsId);
    },

    getGoodsList: function () {
        var goodsList = [];

        for (var i = 0; i < this.goodsId; i++) {
            var goods = this.goodsList.get(i);
            goodsList.push(goods)
        }

        return goodsList
    }

}

module.exports = TheGoods;