package Service.Impl;

import Entity.horse.Horse;
import Service.HorseService;
import Utility.NameEscape;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorseServiceImpl implements HorseService {

    /** 競走馬Listの画面からHorseIdを取得し、IdListを作成する*/
    @Override
    public List<String> getHorseIdList(int i) throws IOException {
        String url = NetkeibaURL.HORSE_LIST_URL + i;
        Document document = Jsoup.connect(url).get();
        List<String> horseIdList = new ArrayList<>();

        Elements elements = document.select("ul.BreederList img");
        for (Element element : elements){
            String target = getId(element);
            horseIdList.add(target);
        }
        return horseIdList;
    }

    //TODO 競走馬詳細から情報を取得し、HorseEntityを作成する
    @Override
    public Horse createHorse(int i){
        List<Horse> horseList = new ArrayList<>();
        try {
            List<String> horseIdList = getHorseIdList(i);

            for (String horseId : horseIdList){
                String URL = NetkeibaURL.HORSE_DATA_URL + horseId;
                Document document = Jsoup.connect(URL).get();
                // TODO Horseにつめるロジックを実装予定

                /** Get HorseData Sample
                 * <div class="db_prof_area_02">
                 *
                 * <table summary="のプロフィール" class="db_prof_table ">
                 * <tr>
                 * <th>生年月日</th>
                 * <td>2017年3月1日</td>
                 * </tr>
                 * <tr>
                 * <th>調教師</th>
                 * <td><a href="/trainer/01075/" title="矢作芳人">矢作芳人</a> (栗東)</td>
                 * </tr>
                 * <tr>
                 * <th>馬主</th>
                 * <td><img src="https://cdn.netkeiba.com/img//db/colours/553800.gif" alt="広尾レース" class="OwnerColours"><a href="/owner/553800/" title="広尾レース">広尾レース</a></td>
                 * </tr>
                 * <tr id="owner_info_tr">
                 * <th>募集情報</th>
                 * <td id="owner_info_td">
                 *
                 * <a href="https://owner.netkeiba.com/?pid=horse_profile&id=2017106711" class="OwnerUnitPrice">1口:2.5万円/<span>2000口</span></a>
                 * </td>
                 * </tr>
                 * <tr>
                 * <th>生産者</th>
                 * <td><a href="/breeder/240543/" title="木村秀則">木村秀則</a></td>
                 * </tr>
                 * <tr>
                 * <th>産地</th>
                 * <td>新ひだか町</td>
                 * </tr>
                 * <tr>
                 * <th>セリ取引価格</th>
                 * <td>
                 * -
                 * </td>
                 * </tr>
                 * <tr>
                 * <th>獲得賞金</th>
                 * <td>
                 *
                 *
                 * 3億170万円 (中央)
                 *
                 * </td>
                 * </tr>
                 * <tr>
                 * <th>通算成績</th>
                 * <td>25戦7勝 [<a href="/horse/result/2017106711/" title="全競走成績">7-6-0-12</a>]</td>
                 * </tr>
                 * <tr>
                 * <th>主な勝鞍</th>
                 * <td><a href="/race/2022J0032607/" title="22'ドバイターフ(G1)">22'ドバイターフ(G1)</a><!--、<a href="/race/2023P0a00208/" title=23'サウジC(G1)"">23'サウジC(G1)</a>--></td>
                 * </tr>
                 * <tr>
                 * <th>近親馬</th>
                 * <td><a href="/horse/2014106280/" title="ディメンシオン">ディメンシオン</a>、<a href="/horse/2009104372/" title="エタンダール">エタンダール</a></td>
                 * </tr>
                 * </table>
                 *
                 * <div class="db_prof_box">
                 * <dl class="fc">
                 * <dt class="DB_ProfHead_dt_01">
                 * <div class="fc">
                 * <p class="db_prof_top_padigree_title png_bg">血統</p>
                 * </div>
                 * </dt>
                 * <dd class="DB_ProfHead_dd_01">
                 * <table cellpadding="0" cellspacing="0" summary="パンサラッサの血統表" class="blood_table">
                 * <tr>
                 * <td rowspan="2" class="b_ml">
                 * <a href="/horse/ped/2008103552/" title="ロードカナロア">ロードカナロア</a>
                 * </td>
                 * <td class="b_ml">
                 * <a href="/horse/ped/2001103460/">キングカメハメハ</a>
                 * </td>
                 * </tr>
                 * <tr>
                 * <td class="b_fml">
                 * <a href="/horse/ped/1996107173/">レディブラッサム</a>
                 * </td>
                 * </tr>
                 * <tr>
                 * <td rowspan="2" class="b_fml">
                 * <a href="/horse/ped/2002110196/">ミスペンバリー</a>
                 * </td>
                 * <td class="b_ml">
                 * <a href="/horse/ped/1996190001/">モンジュー</a>
                 * </td>
                 * </tr>
                 * <tr>
                 * <td class="b_fml">
                 * <a href="/horse/ped/000a0100ee/">Stitching</a>
                 * </td>
                 * </tr>
                 * </table>
                 * </dd>
                 * </dl>
                 * <p class="detail_link"><a href="/horse/ped/2017106711/" title="パンサラッサの血統詳細">血統詳細・兄弟馬</a></p>
                 * </div>
                 * </div><!-- /.db_prof_area_02" -->
                 * */

                System.out.println(horseId + "のDOCUMENTを取得しました");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /** 取得したElementからHorseId部分だけを取得する*/
    private String getId(Element element){
        int startIdNum = element.outerHtml().indexOf("id=") + 3;
        int endIdNum = startIdNum + 10;
        return element.outerHtml().substring(startIdNum, endIdNum);
    }
}
