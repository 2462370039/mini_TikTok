package com.team8.mini_tiktok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.adapter.RankListAdapter;
import com.team8.mini_tiktok.entity.RankList;
import com.team8.mini_tiktok.entity.RankResponse;
import com.team8.mini_tiktok.network.Api;
import com.team8.mini_tiktok.network.ApiConfig;
import com.team8.mini_tiktok.network.MyCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RankActivity extends BaseActivity {

    List<RankList> mData = new ArrayList<>();

    private ListView listView;

    private RankListAdapter mAdapter = null;

    @Override
    protected int initLayout() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.lv_rank);
    }

    @Override
    protected void initDate() {
        //获取Bundle中数据access-token
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Args");
        String type = bundle.getString("type");
        String access_token = bundle.getString("access-token");
        //TODO:if (access-token == "")
        //TODO:删除
        Log.d("TZH", "onCreate: type=" + type);
        Log.d("TZH", "onCreate: access_token=" + access_token);

        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("version", "");

//        getRankList(params, access_token);
        //TODO:删除
        String result = "{\"data\":{\"active_time\":\"2022-09-10\",\"description\":\"\",\"error_code\":0,\"list\":[{\"actors\":[\"吴彦姝\",\"奚美娟\",\"文淇\",\"朱时茂\",\"李晓川\",\"杨恩又\",\"刘洋\"],\"areas\":[\"中国大陆\"],\"directors\":[\"杨荔钠\"],\"discussion_hot\":2865077,\"hot\":10479279,\"id\":\"6805947612902982158\",\"influence_hot\":861490,\"maoyan_id\":\"1435019\",\"name\":\"妈妈！\",\"name_en\":\"Song of Spring\",\"poster\":\"https://p9-dy.byteimg.com/obj/compass/148dd1ad05ba4a6385fa9b617baf9c05?from=552310259\",\"release_date\":\"2022-09-10\",\"search_hot\":1231964,\"tags\":[\"剧情\",\"家庭\"],\"topic_hot\":929213,\"type\":1},{\"actors\":[\"周迅\",\"郑秀文\",\"易烊千玺\",\"许娣\",\"冯德伦\",\"白客\",\"黄米依\",\"鲍起静\",\"朱雅芬\",\"苏小明\",\"巴图\",\"马昕墨\",\"方平\"],\"areas\":[\"中国大陆\"],\"directors\":[\"李少红\",\"陈冲\",\"张艾嘉\"],\"discussion_hot\":2646436,\"hot\":10431984,\"id\":\"6920024822365815309\",\"influence_hot\":783936,\"maoyan_id\":\"1359285\",\"name\":\"世间有她\",\"name_en\":\"Her Story\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/c92912315e6848ed8f09136f91d4767d?from=552310259\",\"release_date\":\"2022-09-09\",\"search_hot\":1027566,\"tags\":[\"剧情\",\"爱情\"],\"topic_hot\":938185,\"type\":1},{\"actors\":[\"陈宝国\",\"马苏\",\"阿云嘎\",\"王锵\",\"罗意淳\",\"王楚然\",\"黄尧\",\"白宇帆\",\"曹骏\",\"丁程鑫\",\"张铭恩\"],\"directors\":[\"尔冬升\"],\"discussion_hot\":2715216,\"hot\":9658915,\"id\":\"7125256567830053389\",\"influence_hot\":715723,\"maoyan_id\":\"\",\"name\":\"海的尽头是草原\",\"name_en\":\"In Search of Lost Time\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/1202734e3e654f8da43f75d430339149?from=552310259\",\"release_date\":\"\",\"search_hot\":1081447,\"tags\":[\"剧情\"],\"topic_hot\":881986,\"type\":1},{\"actors\":[\"惠英红\",\"吴岱融\",\"吴千语\",\"杨天宇\",\"陈贝儿\"],\"directors\":[\"朱凤娴\"],\"discussion_hot\":2463505,\"hot\":9499948,\"id\":\"7130116971005215263\",\"influence_hot\":754707,\"maoyan_id\":\"\",\"name\":\"我的非凡父母\",\"name_en\":\"Sunshine of My Life\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/98d128fe028a4c09a75b2863e4a2e708?from=552310259\",\"release_date\":\"2022-09-02\",\"search_hot\":1022753,\"tags\":[\"剧情\",\"家庭\"],\"topic_hot\":843581,\"type\":1},{\"actors\":[\"张晋\",\"李治廷\",\"蒋璐霞\",\"刘智满\",\"叶浏\",\"张衣\",\"唐国忠\",\"高冬平\",\"薛佳凝\"],\"areas\":[\"中国大陆\"],\"directors\":[\"蒋丛\"],\"discussion_hot\":2611936,\"hot\":9359852,\"id\":\"6799544526592541192\",\"influence_hot\":706584,\"maoyan_id\":\"\",\"name\":\"狼群\",\"name_en\":\"Wolf Pack\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/ed6d25aa5db24fbfba7261a0f06c542d?from=552310259\",\"release_date\":\"2022-09-09\",\"search_hot\":997702,\"tags\":[\"动作\",\"战争\"],\"topic_hot\":849212,\"type\":1},{\"actors\":[\"马丽\",\"常远\",\"魏翔\",\"贾冰\",\"黄允桐\",\"韩彦博\",\"张一鸣\",\"郝鹏飞\",\"李一宁\",\"李志荟\",\"刘语乔\"],\"directors\":[\"张栾\"],\"discussion_hot\":1736073,\"hot\":8646387,\"id\":\"7069702763545887239\",\"influence_hot\":727967,\"maoyan_id\":\"1335230\",\"name\":\"哥，你好\",\"name_en\":\"Give Me Five\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/a1fa7bd3dbf5408d97f4d0948ea95e39?from=552310259\",\"release_date\":\"2022-09-09\",\"search_hot\":1047566,\"tags\":[\"喜剧\",\"剧情\"],\"topic_hot\":876847,\"type\":1},{\"actors\":[\"王千源\",\"谭卓\",\"黄杨钿甜\",\"艾米\",\"周铁\",\"安得路\",\"王大陆\",\"巩金国\"],\"directors\":[\"黄岳泰\"],\"discussion_hot\":2209079,\"hot\":8491527,\"id\":\"7092236761799066143\",\"influence_hot\":751250,\"maoyan_id\":\"1450064\",\"name\":\"世界上最爱我的人\",\"name_en\":\"Beating Heart\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/339704cbb30e41b0aebb1dead800d66b?from=552310259\",\"release_date\":\"2022-08-26\",\"search_hot\":981713,\"tags\":[\"剧情\",\"犯罪\"],\"topic_hot\":762752,\"type\":1},{\"actors\":[\"王凯\",\"季冠霖\",\"李立宏\",\"李兰陵\",\"赵毅\",\"刘校妤\",\"文靖渊\",\"汤水雨\",\"星潮\",\"邱秋\",\"杨天翔\",\"孟宇\",\"杨默\",\"赵铭洲\",\"李楠\",\"林强\",\"刘琮\",\"张怀瑾\",\"沈良骏\",\"赵霁\",\"胡亚捷\",\"韩乐\"],\"directors\":[\"赵霁\"],\"discussion_hot\":2045566,\"hot\":8360495,\"id\":\"7087880505030672933\",\"influence_hot\":854307,\"maoyan_id\":\"1407233\",\"name\":\"新神榜：杨戬\",\"name_en\":\"New Gods：Yang Jian\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/fab4050a90d540faa77a5313f99fa004?from=552310259\",\"release_date\":\"2022-08-19\",\"search_hot\":1072293,\"topic_hot\":842937,\"type\":1},{\"actors\":[\"史蒂夫·卡瑞尔\",\"皮埃尔·柯芬\",\"塔拉吉·P·汉森\",\"杨紫琼\",\"拉塞尔·布兰德\",\"露西·劳莱丝\",\"杜夫·龙格尔\",\"丹尼·特雷霍\",\"尚格·云顿\",\"朱莉·安德鲁斯\",\"艾伦·阿金\",\"凯文·哈特\",\"玛格特·罗比\",\"戴夫·巴蒂斯塔\",\"罗伯特·菲茨杰拉德·迪格斯\"],\"areas\":[\"美国\"],\"directors\":[\"凯尔·巴尔达\",\"布拉德·埃布尔森\",\"何塞·瓦尔德尔·奥马尔\"],\"discussion_hot\":2358192,\"hot\":8279053,\"id\":\"6446666592943079949\",\"influence_hot\":793785,\"maoyan_id\":\"893936\",\"name\":\"小黄人大眼萌：神偷奶爸前传\",\"name_en\":\"Minions 2：The Rise of Gru\",\"poster\":\"https://p9-dy.byteimg.com/obj/compass/4f7418bafc904051b488cee95012125a?from=552310259\",\"release_date\":\"2022-08-19\",\"search_hot\":956289,\"tags\":[\"喜剧\",\"动画\",\"冒险\"],\"topic_hot\":816758,\"type\":1},{\"actors\":[\"黄子华\",\"邓丽欣\",\"张继聪\",\"王菀之\",\"林明祯\",\"陈湛文\",\"廖子妤\"],\"directors\":[\"陈咏燊\"],\"discussion_hot\":2635247,\"hot\":8250584,\"id\":\"7134605210982613535\",\"influence_hot\":0,\"maoyan_id\":\"\",\"name\":\"还是觉得你最好\",\"name_en\":\"Table for Six\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/5adef762b7b54325932c4c07e0e15723?from=552310259\",\"release_date\":\"2022-09-09\",\"search_hot\":0,\"tags\":[\"喜剧\",\"爱情\"],\"topic_hot\":814073,\"type\":1},{\"actors\":[\"尹昉\",\"李梦\",\"吴玉芳\",\"曲博\",\"欧阳小如\"],\"areas\":[\"中国大陆\"],\"directors\":[\"曾晋为\",\"赵祖祥\"],\"discussion_hot\":1890786,\"hot\":7600347,\"id\":\"6531960158816305671\",\"influence_hot\":474096,\"maoyan_id\":\"1048274\",\"name\":\"我要和你在一起\",\"name_en\":\"The Rule of Love of Magic Woman\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/5611e819c1c74fbe983e97199e792e52?from=552310259\",\"release_date\":\"2022-09-09\",\"search_hot\":964974,\"tags\":[\"奇幻\",\"爱情\",\"剧情\"],\"topic_hot\":787123,\"type\":1},{\"actors\":[\"塔哈·拉希姆\",\"朱迪·福斯特\",\"本尼迪克特·康伯巴奇\",\"扎克瑞·莱维\",\"谢琳·伍德蕾\",\"Saamer Usmani\",\"克里·约翰逊\",\"马修·马什\",\"大卫·芬恩\",\"安德烈·雅各布斯\",\"兰利·柯克伍德\",\"史蒂夫·马克\",\"罗伯特·霍布斯\",\"梅丽莎·海登\",\"阿拉·萨菲\",\"埃文·亨斯特\",\"达伦-迈耶\",\"弗朗西斯·乔勒\",\"Clayton Boyd\",\"南希·霍兰德\",\"Meena Rayann\",\"阿瑟·法尔科\"],\"areas\":[\"美国\"],\"directors\":[\"凯文·麦克唐纳\"],\"discussion_hot\":1979483,\"hot\":7020903,\"id\":\"6446446440649589262\",\"influence_hot\":688044,\"maoyan_id\":\"1301026\",\"name\":\"760号犯人\",\"name_en\":\"The Mauritanian\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/53bee564a1ee4f6f89d02feccafa78fc?from=552310259\",\"release_date\":\"2022-08-23\",\"search_hot\":801613,\"tags\":[\"剧情\",\"犯罪\"],\"topic_hot\":534201,\"type\":1},{\"actors\":[\"马思纯\",\"王俊凯\",\"范伟\",\"曾美慧孜\",\"李晓川\",\"赵润南\",\"龚蓓苾\",\"黄璐\",\"莫西子诗\",\"曾慕梅\",\"万茜\",\"刘琳\",\"赵润南\",\"黄璐\",\"曾慕梅\",\"方励\"],\"areas\":[\"中国大陆\"],\"directors\":[\"李玉\"],\"discussion_hot\":2651161,\"hot\":7017197,\"id\":\"6894133081402245639\",\"influence_hot\":893359,\"maoyan_id\":\"1369917\",\"name\":\"断·桥\",\"name_en\":\"The Fallen Bridge\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/ad80a23419ea4e12bc8520378cd8e88a?from=552310259\",\"release_date\":\"2022-08-13\",\"search_hot\":954901,\"tags\":[\"剧情\",\"犯罪\"],\"topic_hot\":583478,\"type\":1},{\"discussion_hot\":2314628,\"hot\":6877950,\"id\":\"7007964301692502558\",\"influence_hot\":0,\"maoyan_id\":\"1416685\",\"name\":\"忠犬帕尔玛\",\"name_en\":\"Пальма\",\"poster\":\"https://p9-dy.byteimg.com/obj/compass/T4SLiIZGQoRjBx?from=552310259\",\"release_date\":\"\",\"search_hot\":0,\"tags\":[\"剧情\",\"家庭\"],\"topic_hot\":683232,\"type\":1},{\"actors\":[\"许烈英\",\"王思蓉\",\"刘子涵\"],\"directors\":[\"陶涛\",\"张琪\",\"秦博\",\"范士广\"],\"discussion_hot\":2226895,\"hot\":6514008,\"id\":\"7039325286676038174\",\"influence_hot\":775767,\"maoyan_id\":\"1429716\",\"name\":\"人间世\",\"name_en\":\"Once Upon a Life\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/7930e8b762a44382bc96621a06398952?from=552310259\",\"release_date\":\"2022-08-19\",\"search_hot\":906363,\"topic_hot\":524870,\"type\":1},{\"actors\":[\"古天乐\",\"刘青云\",\"刘嘉玲\",\"姜皓文\",\"谢君豪\",\"吴倩\",\"万国鹏\",\"张家辉\",\"刘浩良\",\"麦天枢\",\"朱鉴然\",\"程小夏\"],\"areas\":[\"香港\"],\"directors\":[\"吴炫辉\"],\"discussion_hot\":2635847,\"hot\":6444475,\"id\":\"6446787399010746893\",\"influence_hot\":911526,\"maoyan_id\":\"249033\",\"name\":\"明日战记\",\"name_en\":\"Warriors of Future\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/5d9299715de44f1db6645f3466d73173?from=552310259\",\"release_date\":\"2022-08-05\",\"search_hot\":1076983,\"tags\":[\"动作\",\"科幻\"],\"topic_hot\":908828,\"type\":1},{\"discussion_hot\":1876897,\"hot\":6121470,\"id\":\"7003222503388774942\",\"influence_hot\":0,\"maoyan_id\":\"\",\"name\":\"雀斑公主 竜とそばかすの姫\",\"name_en\":\"\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/ba0917ccf7294e44a230771b6635ee40?from=552310259\",\"release_date\":\"\",\"search_hot\":0,\"tags\":[\"动画\",\"奇幻\"],\"topic_hot\":448333,\"type\":1},{\"actors\":[\"沈腾\",\"马丽\",\"常远\",\"李诚儒\",\"黄才伦\",\"辣目洋子\",\"郝瀚\",\"黄子韬\",\"王成思\",\"高海宝\",\"杨铮\",\"史彭元\",\"张熙然\",\"黄若萌\",\"杨皓宇\"],\"directors\":[\"张吃鱼\"],\"discussion_hot\":2651501,\"hot\":5542848,\"id\":\"6903365126108381703\",\"influence_hot\":961227,\"maoyan_id\":\"1359034\",\"name\":\"独行月球\",\"name_en\":\"Moon Man\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/93204d13cf5a4fb080e74ea6d057eca1?from=552310259\",\"release_date\":\"2022-07-29\",\"search_hot\":1088760,\"topic_hot\":938229,\"type\":1},{\"actors\":[\"李汶翰\",\"徐若晗\",\"王博文\",\"高秋梓\",\"柯蓝\",\"苇青\"],\"directors\":[\"落落\"],\"discussion_hot\":2415372,\"hot\":4833408,\"id\":\"7073662696507474446\",\"influence_hot\":881662,\"maoyan_id\":\"1331205\",\"name\":\"遇见你\",\"name_en\":\"Almost Love\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/c2204b96045341ab9c2eaa6c33edeef6?from=552310259\",\"release_date\":\"2022-08-04\",\"search_hot\":1069282,\"topic_hot\":749839,\"type\":1},{\"actors\":[\"刘琮\",\"艾一艾\",\"王凯\",\"张喆\",\"张磊\",\"王冠男\",\"林兰\",\"张遥函\",\"程寅\",\"刘若班\",\"陈子平\",\"巫蛊悠悠\"],\"areas\":[\"中国大陆\"],\"directors\":[\"黄健明\"],\"discussion_hot\":1637344,\"hot\":4059110,\"id\":\"6531973976749507076\",\"influence_hot\":0,\"maoyan_id\":\"346331\",\"name\":\"山海经之再见怪兽\",\"name_en\":\"Goodbye Monster\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/392d2ab13a6740038f526afc067b22ca?from=552310259\",\"release_date\":\"2022-08-13\",\"search_hot\":0,\"tags\":[\"动画\"],\"topic_hot\":482240,\"type\":1},{\"actors\":[\"武仁林\",\"海清\",\"杨光锐\",\"武赟志\",\"李生甫\",\"张敏\",\"赵登平\",\"王彩兰\",\"曾建贵\",\"马占红\",\"王翠兰\",\"续彩霞\"],\"directors\":[\"李睿珺\"],\"discussion_hot\":2997510,\"hot\":3781750,\"id\":\"7065249891530113543\",\"influence_hot\":866731,\"maoyan_id\":\"1336601\",\"name\":\"隐入尘烟\",\"name_en\":\"Return to Dust\",\"poster\":\"https://p9-dy.byteimg.com/obj/compass/89b1dda86dfd43ecac9e2e1c22f1a9dd?from=552310259\",\"release_date\":\"2022-07-08\",\"search_hot\":1289795,\"tags\":[\"剧情\",\"爱情\"],\"topic_hot\":1163239,\"type\":1},{\"actors\":[\"倪妮\",\"张鲁一\",\"辛柏青\",\"池松壮亮\",\"中野良子\",\"新音\",\"王佳佳\",\"耐安\",\"毛乐\"],\"areas\":[\"中国大陆\"],\"directors\":[\"张律\"],\"discussion_hot\":2017368,\"hot\":3494909,\"id\":\"7118672661575959076\",\"influence_hot\":0,\"maoyan_id\":\"\",\"name\":\"漫长的告白\",\"name_en\":\"Yanagawa\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/15f516da827b4233a5a9b25be3fa09b9?from=552310259\",\"release_date\":\"2022-08-12\",\"search_hot\":0,\"tags\":[\"剧情\",\"爱情\",\"家庭\"],\"topic_hot\":183555,\"type\":1},{\"actors\":[\"王千源\",\"郭晓东\",\"王迅\",\"许龄月\",\"马渝捷\",\"倪大红\",\"关晓彤\",\"张兆辉\",\"张光北\",\"童蕾\",\"郭晓峰\",\"杨洛仟\"],\"areas\":[\"中国大陆\"],\"directors\":[\"郭晓峰\"],\"discussion_hot\":1979459,\"hot\":3440587,\"id\":\"6858859543187849741\",\"influence_hot\":0,\"maoyan_id\":\"1302480\",\"name\":\"猎屠\",\"name_en\":\"Butcher Hunter\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/a5de28bf4370a36f5e8261f007faee35?from=552310259\",\"release_date\":\"2022-06-03\",\"search_hot\":0,\"topic_hot\":481473,\"type\":1},{\"actors\":[\"王成思\",\"于莎莎\",\"许慧强\",\"艾然\",\"冯秦川\",\"刘锡明\",\"常远\",\"魏翔\",\"章小军\"],\"directors\":[\"徐林\",\"杨沅翰\"],\"discussion_hot\":789447,\"hot\":3253615,\"id\":\"7130842192973103646\",\"influence_hot\":0,\"maoyan_id\":\"\",\"name\":\"爱情的代驾\",\"name_en\":\"Super Substitute\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/4498b24552fd4e78925c07caff5b254a?from=552310259\",\"release_date\":\"2022-08-26\",\"search_hot\":0,\"tags\":[\"剧情\",\"爱情\",\"喜剧\"],\"topic_hot\":229239,\"type\":1},{\"actors\":[\"陆双\",\"谢蔚\",\"吉莹\",\"吴海涛\",\"白文显\",\"陈志荣\"],\"directors\":[\"陆锦辉\",\"钟彧\"],\"discussion_hot\":1312926,\"hot\":3200285,\"id\":\"7069703033898140168\",\"influence_hot\":523142,\"maoyan_id\":\"1358119\",\"name\":\"猪猪侠大电影·海洋日记\",\"name_en\":\"GG BOND：Ocean Mission\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/dcef320b1d774b07908a90b7b26a4fdd?from=552310259\",\"release_date\":\"\",\"search_hot\":602066,\"topic_hot\":145479,\"type\":1},{\"actors\":[\"何政军\",\"来喜\",\"李健\",\"翟小兴\",\"邵晓江\"],\"directors\":[\"周德新\"],\"discussion_hot\":1842846,\"hot\":3087496,\"id\":\"7124510627460514341\",\"influence_hot\":803985,\"maoyan_id\":\"\",\"name\":\"乡土\",\"name_en\":\"\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/0dcb51c7de214e76b1266fb495a3c672?from=552310259\",\"release_date\":\"\",\"search_hot\":485554,\"tags\":[\"剧情\"],\"topic_hot\":0,\"type\":1},{\"actors\":[\"李孝谦\",\"漆昱辰\",\"林俊毅\",\"修雨秀\",\"许童心\",\"丁冠森\",\"李光洁\",\"周深\",\"沈月\",\"张奕聪\",\"高至霆\",\"孙浩\",\"姜卓君\",\"张熙然\",\"夏子轩\"],\"directors\":[\"王梓骏\"],\"discussion_hot\":2060761,\"hot\":2933926,\"id\":\"7077392575660818980\",\"influence_hot\":0,\"maoyan_id\":\"1310222\",\"name\":\"我们的样子像极了爱情\",\"name_en\":\"Close to Love\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/ec798d50b0bd4053a8436b26d31f0aaa?from=552310259\",\"release_date\":\"2022-08-04\",\"search_hot\":0,\"topic_hot\":504332,\"type\":1},{\"actors\":[\"刘青云\",\"蔡卓妍\",\"林峯\",\"谭凯\",\"陈家乐\",\"汤怡\",\"何珮瑜\",\"李若彤\",\"吴浩康\",\"洪天明\",\"车婉婉\",\"斌子\",\"李菁\",\"马志威\",\"杨天宇\",\"胡子彤\",\"朱鉴然\",\"马睿瀚\",\"何佩瑜\",\"斌子\"],\"areas\":[\"香港\"],\"directors\":[\"韦家辉\"],\"discussion_hot\":2104505,\"hot\":2695746,\"id\":\"6531972731066384900\",\"influence_hot\":912655,\"maoyan_id\":\"1203439\",\"name\":\"神探大战\",\"name_en\":\"Cold Detective\",\"poster\":\"https://p9-dy.byteimg.com/obj/compass/73a0215e0c2d41efb338aa248e6d2978?from=552310259\",\"release_date\":\"2022-07-08\",\"search_hot\":939896,\"tags\":[\"动作\",\"悬疑\",\"犯罪\"],\"topic_hot\":725111,\"type\":1},{\"actors\":[\"周依然\",\"吴念轩\",\"汤加文\",\"翁楚汉\",\"吴彦姝\",\"张歆艺\",\"袁弘\",\"谢治勋\",\"耿军\",\"詹妮\"],\"directors\":[\"吴家凯\",\"张智鸿\"],\"discussion_hot\":2094801,\"hot\":2551423,\"id\":\"7074839902659183111\",\"influence_hot\":794919,\"maoyan_id\":\"1331600\",\"name\":\"一直一直都很喜欢你\",\"name_en\":\"Love Can't Be Said\",\"poster\":\"https://p3-dy.byteimg.com/obj/compass/d1c81cff25824f84a921fd9300c5ca97?from=552310259\",\"release_date\":\"2022-07-22\",\"search_hot\":823105,\"topic_hot\":554648,\"type\":1},{\"actors\":[\"杨凝\",\"李兰陵\",\"刘知否\",\"瞳音\",\"常文涛\",\"佟心竹\",\"山新\",\"图特哈蒙\",\"阎么么\",\"王琳熙\"],\"directors\":[\"王云飞\"],\"discussion_hot\":1406726,\"hot\":2416212,\"id\":\"7118672168472281631\",\"influence_hot\":790859,\"maoyan_id\":\"\",\"name\":\"疯了！桂宝之三星夺宝\",\"name_en\":\"CRAZY.KWAI.BOO Sanxingdui Spirited Away\",\"poster\":\"https://p1-dy.byteimg.com/obj/compass/078a28d12cae4e3daa3ab6092fe17c56?from=552310259\",\"release_date\":\"2022-07-29\",\"search_hot\":501120,\"topic_hot\":194723,\"type\":1}]},\"extra\":{\"logid\":\"20220910173159010151063047028262F4\",\"now\":1662802319910}}";
        Gson gson = new Gson();
        RankResponse response = gson.fromJson(result, RankResponse.class);
        mData = response.getData().getRankList();

        mAdapter = new RankListAdapter(mData, mContext);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, "点击了第" + i + "部", Toast.LENGTH_SHORT).show();
            }
        });

        //请求数据
        //未封装
//        HttpUrl.Builder builder = HttpUrl.parse(ApiConfig.SERVER_ADDRESS + ApiConfig.GET_RANK).newBuilder();
//        for (Map.Entry<String, Object> entry : params.entrySet()){
//            builder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
//        }
//        HttpUrl httpUrl = builder.build();
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(httpUrl.toString())
//                .addHeader("contentType", "application/json")
//                .addHeader("access-token", access_token)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                Log.e("TZH", "onFailure: ", e);
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String res = response.body().string();
//                Log.d("TZH", "onResponse: res=" + res);
//                TextView tvres = findViewById(R.id.tv_rank);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvres.setText(res);
//                    }
//                });
//            }
//        });
    }

    private void getRankList(HashMap<String, Object> params, String access_token){
        Api.config(ApiConfig.GET_RANK, params).getRequest("application/json", access_token,  new MyCallBack() {
            @Override
            public void onSuccess(String res) {
                Gson gson = new Gson();
                RankResponse response = gson.fromJson(res, RankResponse.class);
                mData = response.getData().getRankList();
                //TODO:绑定数据
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}