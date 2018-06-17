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
                title: "Gakki 我老婆",
                content: "Gakki 我老婆",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/gakki.gif",
                label: "Gakki"
            },
            {
                title: "好利来冰山熔岩巧克力",
                content: "爆肝推荐的好利来新品！冷热两吃的巧克力蛋糕给你味蕾上的双重盛宴~冷冻是生巧的口感，丝滑浓郁，切片配上红茶简直完美~加热就变成熔岩蛋糕，巧克力炙热流淌，一口就是满满的幸福感~吃货赶快趁包邮好价入手试试吧！",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/52111eee2bb34c36ac493ac5650858501528978507128.jpg@768w_1l",
                label: "巧克力"
            },
            {
                title: "可口可乐北极熊唇彩",
                content: "你亲吻过可乐味的女孩么？",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/c6eeabfa552b407c90de1ab4606017111528980289989.jpg@768w_1l",
                label: "唇彩"
            },
            {
                title: "洛斐机械键盘",
                content: "作为一名外貌党，我推荐洛斐的这款键盘。外表圆润可爱，键帽为圆点状，手感也是相当不错。使用的是GATERON家的青轴轴体，按下去会有“咔哒”的声音，段落感很强，打起字来节奏感十足。",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/f7dc02a8529d416eaf75fe7d52c29ba91528979979855.png@768w_1l",
                label: "键盘"
            },
            {
                title: "chocoMe草莓牛奶黑巧克力礼盒",
                content: "来自匈牙利的高端巧克力品牌。冻干草莓镶嵌在整块的巧克力上，还有丰富的坚果，光是外形就已经很吸引眼球了！草莓的酸甜与巧克力的醇滑相结合，香浓可口，简直是视觉味觉双丰收!",
                url: "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/nebulas-market/aea7901d546f47f9ba3e16da59d3707a1528979183699.jpg@768w_1l",
                label: "巧克力"
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