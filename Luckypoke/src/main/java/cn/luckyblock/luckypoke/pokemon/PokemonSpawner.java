package cn.luckyblock.luckypoke.pokemon;

import cn.luckyblock.luckypoke.LuckyCobbleBlock;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class PokemonSpawner {

    // 英文宝可梦名称到中文的映射（参考神奇宝贝百科）
    private static final Map<String, String> POKEMON_NAME_MAP = new HashMap<>();
    static {
        // 第一世代
        POKEMON_NAME_MAP.put("Bulbasaur", "妙蛙种子");
        POKEMON_NAME_MAP.put("Ivysaur", "妙蛙草");
        POKEMON_NAME_MAP.put("Venusaur", "妙蛙花");
        POKEMON_NAME_MAP.put("Charmander", "小火龙");
        POKEMON_NAME_MAP.put("Charmeleon", "火恐龙");
        POKEMON_NAME_MAP.put("Charizard", "喷火龙");
        POKEMON_NAME_MAP.put("Squirtle", "杰尼龟");
        POKEMON_NAME_MAP.put("Wartortle", "卡咪龟");
        POKEMON_NAME_MAP.put("Blastoise", "水箭龟");
        POKEMON_NAME_MAP.put("Caterpie", "绿毛虫");
        POKEMON_NAME_MAP.put("Metapod", "铁甲蛹");
        POKEMON_NAME_MAP.put("Butterfree", "巴大蝶");
        POKEMON_NAME_MAP.put("Weedle", "独角虫");
        POKEMON_NAME_MAP.put("Kakuna", "铁壳蛹");
        POKEMON_NAME_MAP.put("Beedrill", "大针蜂");
        POKEMON_NAME_MAP.put("Pidgey", "波波");
        POKEMON_NAME_MAP.put("Pidgeotto", "比比鸟");
        POKEMON_NAME_MAP.put("Pidgeot", "大比鸟");
        POKEMON_NAME_MAP.put("Rattata", "小拉达");
        POKEMON_NAME_MAP.put("Raticate", "拉达");
        POKEMON_NAME_MAP.put("Spearow", "烈雀");
        POKEMON_NAME_MAP.put("Fearow", "大嘴雀");
        POKEMON_NAME_MAP.put("Ekans", "阿柏蛇");
        POKEMON_NAME_MAP.put("Arbok", "阿柏怪");
        POKEMON_NAME_MAP.put("Pikachu", "皮卡丘");
        POKEMON_NAME_MAP.put("Raichu", "雷丘");
        POKEMON_NAME_MAP.put("Sandshrew", "穿山鼠");
        POKEMON_NAME_MAP.put("Sandslash", "穿山王");
        POKEMON_NAME_MAP.put("NidoranF", "尼多兰");
        POKEMON_NAME_MAP.put("Nidorina", "尼多娜");
        POKEMON_NAME_MAP.put("Nidoqueen", "尼多后");
        POKEMON_NAME_MAP.put("NidoranM", "尼多朗");
        POKEMON_NAME_MAP.put("Nidorino", "尼多力诺");
        POKEMON_NAME_MAP.put("Nidoking", "尼多王");
        POKEMON_NAME_MAP.put("Clefairy", "皮皮");
        POKEMON_NAME_MAP.put("Clefable", "皮可西");
        POKEMON_NAME_MAP.put("Vulpix", "六尾");
        POKEMON_NAME_MAP.put("Ninetales", "九尾");
        POKEMON_NAME_MAP.put("Jigglypuff", "胖丁");
        POKEMON_NAME_MAP.put("Wigglytuff", "胖可丁");
        POKEMON_NAME_MAP.put("Zubat", "超音蝠");
        POKEMON_NAME_MAP.put("Golbat", "大嘴蝠");
        POKEMON_NAME_MAP.put("Oddish", "走路草");
        POKEMON_NAME_MAP.put("Gloom", "臭臭花");
        POKEMON_NAME_MAP.put("Vileplume", "霸王花");
        POKEMON_NAME_MAP.put("Paras", "派拉斯");
        POKEMON_NAME_MAP.put("Parasect", "派拉斯特");
        POKEMON_NAME_MAP.put("Venonat", "毛球");
        POKEMON_NAME_MAP.put("Venomoth", "摩鲁蛾");
        POKEMON_NAME_MAP.put("Diglett", "地鼠");
        POKEMON_NAME_MAP.put("Dugtrio", "三地鼠");
        POKEMON_NAME_MAP.put("Meowth", "喵喵");
        POKEMON_NAME_MAP.put("Persian", "猫老大");
        POKEMON_NAME_MAP.put("Psyduck", "可达鸭");
        POKEMON_NAME_MAP.put("Golduck", "哥达鸭");
        POKEMON_NAME_MAP.put("Mankey", "猴怪");
        POKEMON_NAME_MAP.put("Primeape", "火暴猴");
        POKEMON_NAME_MAP.put("Growlithe", "卡蒂狗");
        POKEMON_NAME_MAP.put("Arcanine", "风速狗");
        POKEMON_NAME_MAP.put("Poliwag", "蚊香蝌蚪");
        POKEMON_NAME_MAP.put("Poliwhirl", "蚊香君");
        POKEMON_NAME_MAP.put("Poliwrath", "蚊香泳士");
        POKEMON_NAME_MAP.put("Abra", "凯西");
        POKEMON_NAME_MAP.put("Kadabra", "勇基拉");
        POKEMON_NAME_MAP.put("Alakazam", "胡地");
        POKEMON_NAME_MAP.put("Machop", "腕力");
        POKEMON_NAME_MAP.put("Machoke", "豪力");
        POKEMON_NAME_MAP.put("Machamp", "怪力");
        POKEMON_NAME_MAP.put("Bellsprout", "喇叭芽");
        POKEMON_NAME_MAP.put("Weepinbell", "口呆花");
        POKEMON_NAME_MAP.put("Victreebel", "大食花");
        POKEMON_NAME_MAP.put("Tentacool", "玛瑙水母");
        POKEMON_NAME_MAP.put("Tentacruel", "毒刺水母");
        POKEMON_NAME_MAP.put("Geodude", "小拳石");
        POKEMON_NAME_MAP.put("Graveler", "隆隆石");
        POKEMON_NAME_MAP.put("Golem", "隆隆岩");
        POKEMON_NAME_MAP.put("Ponyta", "小火马");
        POKEMON_NAME_MAP.put("Rapidash", "烈焰马");
        POKEMON_NAME_MAP.put("Slowpoke", "呆呆兽");
        POKEMON_NAME_MAP.put("Slowbro", "呆壳兽");
        POKEMON_NAME_MAP.put("Magnemite", "小磁怪");
        POKEMON_NAME_MAP.put("Magneton", "三合一磁怪");
        POKEMON_NAME_MAP.put("Farfetchd", "大葱鸭");
        POKEMON_NAME_MAP.put("Doduo", "嘟嘟");
        POKEMON_NAME_MAP.put("Dodrio", "嘟嘟利");
        POKEMON_NAME_MAP.put("Seel", "小海狮");
        POKEMON_NAME_MAP.put("Dewgong", "白海狮");
        POKEMON_NAME_MAP.put("Grimer", "臭泥");
        POKEMON_NAME_MAP.put("Muk", "臭臭泥");
        POKEMON_NAME_MAP.put("Shellder", "大舌贝");
        POKEMON_NAME_MAP.put("Cloyster", "铁甲贝");
        POKEMON_NAME_MAP.put("Gastly", "鬼斯");
        POKEMON_NAME_MAP.put("Haunter", "鬼斯通");
        POKEMON_NAME_MAP.put("Gengar", "耿鬼");
        POKEMON_NAME_MAP.put("Onix", "大岩蛇");
        POKEMON_NAME_MAP.put("Drowzee", "素利普");
        POKEMON_NAME_MAP.put("Hypno", "素利拍");
        POKEMON_NAME_MAP.put("Krabby", "大钳蟹");
        POKEMON_NAME_MAP.put("Kingler", "巨钳蟹");
        POKEMON_NAME_MAP.put("Voltorb", "雷电球");
        POKEMON_NAME_MAP.put("Electrode", "顽皮雷弹");
        POKEMON_NAME_MAP.put("Exeggcute", "蛋蛋");
        POKEMON_NAME_MAP.put("Exeggutor", "椰蛋树");
        POKEMON_NAME_MAP.put("Cubone", "卡拉卡拉");
        POKEMON_NAME_MAP.put("Marowak", "嘎啦嘎啦");
        POKEMON_NAME_MAP.put("Hitmonlee", "飞腿郎");
        POKEMON_NAME_MAP.put("Hitmonchan", "快拳郎");
        POKEMON_NAME_MAP.put("Lickitung", "大舌头");
        POKEMON_NAME_MAP.put("Koffing", "瓦斯弹");
        POKEMON_NAME_MAP.put("Weezing", "双弹瓦斯");
        POKEMON_NAME_MAP.put("Rhyhorn", "独角犀牛");
        POKEMON_NAME_MAP.put("Rhydon", "钻角犀兽");
        POKEMON_NAME_MAP.put("Chansey", "吉利蛋");
        POKEMON_NAME_MAP.put("Tangela", "蔓藤怪");
        POKEMON_NAME_MAP.put("Kangaskhan", "袋兽");
        POKEMON_NAME_MAP.put("Horsea", "墨海马");
        POKEMON_NAME_MAP.put("Seadra", "海刺龙");
        POKEMON_NAME_MAP.put("Goldeen", "角金鱼");
        POKEMON_NAME_MAP.put("Seaking", "金鱼王");
        POKEMON_NAME_MAP.put("Staryu", "海星星");
        POKEMON_NAME_MAP.put("Starmie", "宝石海星");
        POKEMON_NAME_MAP.put("MrMime", "魔墙人偶");
        POKEMON_NAME_MAP.put("Scyther", "飞天螳螂");
        POKEMON_NAME_MAP.put("Jynx", "迷唇姐");
        POKEMON_NAME_MAP.put("Electabuzz", "电击兽");
        POKEMON_NAME_MAP.put("Magmar", "鸭嘴火兽");
        POKEMON_NAME_MAP.put("Pinsir", "大甲");
        POKEMON_NAME_MAP.put("Tauros", "肯泰罗");
        POKEMON_NAME_MAP.put("Magikarp", "鲤鱼王");
        POKEMON_NAME_MAP.put("Gyarados", "暴鲤龙");
        POKEMON_NAME_MAP.put("Lapras", "乘龙");
        POKEMON_NAME_MAP.put("Ditto", "百变怪");
        POKEMON_NAME_MAP.put("Eevee", "伊布");
        POKEMON_NAME_MAP.put("Vaporeon", "水伊布");
        POKEMON_NAME_MAP.put("Jolteon", "雷伊布");
        POKEMON_NAME_MAP.put("Flareon", "火伊布");
        POKEMON_NAME_MAP.put("Porygon", "多边兽");
        POKEMON_NAME_MAP.put("Omanyte", "菊石兽");
        POKEMON_NAME_MAP.put("Omastar", "多刺菊石兽");
        POKEMON_NAME_MAP.put("Kabuto", "化石盔");
        POKEMON_NAME_MAP.put("Kabutops", "镰刀盔");
        POKEMON_NAME_MAP.put("Aerodactyl", "化石翼龙");
        POKEMON_NAME_MAP.put("Snorlax", "卡比兽");
        POKEMON_NAME_MAP.put("Articuno", "急冻鸟");
        POKEMON_NAME_MAP.put("Zapdos", "闪电鸟");
        POKEMON_NAME_MAP.put("Moltres", "火焰鸟");
        POKEMON_NAME_MAP.put("Dratini", "迷你龙");
        POKEMON_NAME_MAP.put("Dragonair", "哈克龙");
        POKEMON_NAME_MAP.put("Dragonite", "快龙");
        POKEMON_NAME_MAP.put("Mewtwo", "超梦");
        POKEMON_NAME_MAP.put("Mew", "梦幻");
        
        // 第二世代
        POKEMON_NAME_MAP.put("Chikorita", "菊草叶");
        POKEMON_NAME_MAP.put("Bayleef", "月桂叶");
        POKEMON_NAME_MAP.put("Meganium", "大竺葵");
        POKEMON_NAME_MAP.put("Cyndaquil", "火球鼠");
        POKEMON_NAME_MAP.put("Quilava", "火岩鼠");
        POKEMON_NAME_MAP.put("Typhlosion", "火暴兽");
        POKEMON_NAME_MAP.put("Totodile", "小锯鳄");
        POKEMON_NAME_MAP.put("Croconaw", "蓝鳄");
        POKEMON_NAME_MAP.put("Feraligatr", "大力鳄");
        POKEMON_NAME_MAP.put("Ho-oh", "凤王");
        POKEMON_NAME_MAP.put("Lugia", "洛奇亚");
        POKEMON_NAME_MAP.put("Raikou", "雷公");
        POKEMON_NAME_MAP.put("Entei", "炎帝");
        POKEMON_NAME_MAP.put("Suicune", "水君");
        POKEMON_NAME_MAP.put("Celebi", "时拉比");
        
        // 第三世代
        POKEMON_NAME_MAP.put("Treecko", "木守宫");
        POKEMON_NAME_MAP.put("Grovyle", "森林蜥蜴");
        POKEMON_NAME_MAP.put("Sceptile", "蜥蜴王");
        POKEMON_NAME_MAP.put("Torchic", "火稚鸡");
        POKEMON_NAME_MAP.put("Combusken", "力壮鸡");
        POKEMON_NAME_MAP.put("Blaziken", "火焰鸡");
        POKEMON_NAME_MAP.put("Mudkip", "水跃鱼");
        POKEMON_NAME_MAP.put("Marshtomp", "沼跃鱼");
        POKEMON_NAME_MAP.put("Swampert", "巨沼怪");
        POKEMON_NAME_MAP.put("Regirock", "雷吉洛克");
        POKEMON_NAME_MAP.put("Regice", "雷吉艾斯");
        POKEMON_NAME_MAP.put("Registeel", "雷吉斯奇鲁");
        POKEMON_NAME_MAP.put("Latias", "拉帝亚斯");
        POKEMON_NAME_MAP.put("Latios", "拉帝欧斯");
        POKEMON_NAME_MAP.put("Groudon", "固拉多");
        POKEMON_NAME_MAP.put("Kyogre", "盖欧卡");
        POKEMON_NAME_MAP.put("Rayquaza", "裂空座");
        POKEMON_NAME_MAP.put("Jirachi", "基拉祈");
        POKEMON_NAME_MAP.put("Deoxys", "代欧奇希斯");
        
        // 第四世代
        POKEMON_NAME_MAP.put("Turtwig", "草苗龟");
        POKEMON_NAME_MAP.put("Grotle", "树林龟");
        POKEMON_NAME_MAP.put("Torterra", "土台龟");
        POKEMON_NAME_MAP.put("Chimchar", "小火焰猴");
        POKEMON_NAME_MAP.put("Monferno", "猛火猴");
        POKEMON_NAME_MAP.put("Infernape", "烈焰猴");
        POKEMON_NAME_MAP.put("Piplup", "波加曼");
        POKEMON_NAME_MAP.put("Prinplup", "波皇子");
        POKEMON_NAME_MAP.put("Empoleon", "帝王拿波");
        POKEMON_NAME_MAP.put("Uxie", "由克希");
        POKEMON_NAME_MAP.put("Mesprit", "艾姆利多");
        POKEMON_NAME_MAP.put("Azelf", "亚克诺姆");
        POKEMON_NAME_MAP.put("Dialga", "帝牙卢卡");
        POKEMON_NAME_MAP.put("Palkia", "帕路奇亚");
        POKEMON_NAME_MAP.put("Heatran", "席多蓝恩");
        POKEMON_NAME_MAP.put("Regigigas", "雷吉奇卡斯");
        POKEMON_NAME_MAP.put("Giratina", "骑拉帝纳");
        POKEMON_NAME_MAP.put("Cresselia", "克雷色利亚");
        POKEMON_NAME_MAP.put("Phione", "霏欧纳");
        POKEMON_NAME_MAP.put("Manaphy", "玛纳霏");
        POKEMON_NAME_MAP.put("Darkrai", "达克莱伊");
        POKEMON_NAME_MAP.put("Shaymin", "谢米");
        POKEMON_NAME_MAP.put("Arceus", "阿尔宙斯");
        
        // 第五世代
        POKEMON_NAME_MAP.put("Snivy", "藤藤蛇");
        POKEMON_NAME_MAP.put("Servine", "青藤蛇");
        POKEMON_NAME_MAP.put("Serperior", "君主蛇");
        POKEMON_NAME_MAP.put("Tepig", "暖暖猪");
        POKEMON_NAME_MAP.put("Pignite", "炒炒猪");
        POKEMON_NAME_MAP.put("Emboar", "炎武王");
        POKEMON_NAME_MAP.put("Oshawott", "水水獭");
        POKEMON_NAME_MAP.put("Dewott", "双刃丸");
        POKEMON_NAME_MAP.put("Samurott", "大剑鬼");
        POKEMON_NAME_MAP.put("Victini", "比克提尼");
        POKEMON_NAME_MAP.put("Reshiram", "莱希拉姆");
        POKEMON_NAME_MAP.put("Zekrom", "捷克罗姆");
        POKEMON_NAME_MAP.put("Kyurem", "酋雷姆");
        POKEMON_NAME_MAP.put("Cobalion", "勾帕路翁");
        POKEMON_NAME_MAP.put("Terrakion", "代拉基翁");
        POKEMON_NAME_MAP.put("Virizion", "毕力吉翁");
        POKEMON_NAME_MAP.put("Tornadus", "龙卷云");
        POKEMON_NAME_MAP.put("Thundurus", "雷电云");
        POKEMON_NAME_MAP.put("Landorus", "土地云");
        POKEMON_NAME_MAP.put("Keldeo", "凯路迪欧");
        POKEMON_NAME_MAP.put("Meloetta", "美洛耶塔");
        POKEMON_NAME_MAP.put("Genesect", "盖诺赛克特");
        
        // 第六世代
        POKEMON_NAME_MAP.put("Chespin", "哈力栗");
        POKEMON_NAME_MAP.put("Quilladin", "胖胖哈力");
        POKEMON_NAME_MAP.put("Chesnaught", "布里卡隆");
        POKEMON_NAME_MAP.put("Fennekin", "火狐狸");
        POKEMON_NAME_MAP.put("Braixen", "长尾火狐");
        POKEMON_NAME_MAP.put("Delphox", "妖火红狐");
        POKEMON_NAME_MAP.put("Froakie", "呱呱泡蛙");
        POKEMON_NAME_MAP.put("Frogadier", "呱头蛙");
        POKEMON_NAME_MAP.put("Greninja", "甲贺忍蛙");
        POKEMON_NAME_MAP.put("Xerneas", "哲尔尼亚斯");
        POKEMON_NAME_MAP.put("Yveltal", "伊裴尔塔尔");
        POKEMON_NAME_MAP.put("Zygarde", "基格尔德");
        POKEMON_NAME_MAP.put("Diancie", "蒂安希");
        POKEMON_NAME_MAP.put("Hoopa", "胡帕");
        POKEMON_NAME_MAP.put("Volcanion", "波尔凯尼恩");
        
        // 第七世代
        POKEMON_NAME_MAP.put("Rowlet", "木木枭");
        POKEMON_NAME_MAP.put("Dartrix", "投羽枭");
        POKEMON_NAME_MAP.put("Decidueye", "狙射树枭");
        POKEMON_NAME_MAP.put("Litten", "火斑喵");
        POKEMON_NAME_MAP.put("Torracat", "炎热喵");
        POKEMON_NAME_MAP.put("Incineroar", "炽焰咆哮虎");
        POKEMON_NAME_MAP.put("Popplio", "球球海狮");
        POKEMON_NAME_MAP.put("Brionne", "花漾海狮");
        POKEMON_NAME_MAP.put("Primarina", "西狮海壬");
        POKEMON_NAME_MAP.put("Solgaleo", "索尔迦雷欧");
        POKEMON_NAME_MAP.put("Lunala", "露奈雅拉");
        POKEMON_NAME_MAP.put("Nihilego", "虚吾伊德");
        POKEMON_NAME_MAP.put("Buzzwole", "爆肌蚊");
        POKEMON_NAME_MAP.put("Pheromosa", "费洛美螂");
        POKEMON_NAME_MAP.put("Xurkitree", "电束木");
        POKEMON_NAME_MAP.put("Celesteela", "铁火辉夜");
        POKEMON_NAME_MAP.put("Kartana", "纸御剑");
        POKEMON_NAME_MAP.put("Guzzlord", "恶食大王");
        POKEMON_NAME_MAP.put("Necrozma", "奈克洛兹玛");
        POKEMON_NAME_MAP.put("Magearna", "玛机雅娜");
        POKEMON_NAME_MAP.put("Marshadow", "玛夏多");
        
        // 第八世代
        POKEMON_NAME_MAP.put("Grookey", "敲音猴");
        POKEMON_NAME_MAP.put("Thwackey", "啪咚猴");
        POKEMON_NAME_MAP.put("Rillaboom", "轰擂金刚猩");
        POKEMON_NAME_MAP.put("Scorbunny", "炎兔儿");
        POKEMON_NAME_MAP.put("Raboot", "腾蹴小将");
        POKEMON_NAME_MAP.put("Cinderace", "闪焰王牌");
        POKEMON_NAME_MAP.put("Sobble", "泪眼蜥");
        POKEMON_NAME_MAP.put("Drizzile", "变涩蜥");
        POKEMON_NAME_MAP.put("Inteleon", "千面避役");
        POKEMON_NAME_MAP.put("Zacian", "苍响");
        POKEMON_NAME_MAP.put("Zamazenta", "藏玛然特");
        POKEMON_NAME_MAP.put("Eternatus", "无极汰那");
        POKEMON_NAME_MAP.put("Kubfu", "武道熊师");
        POKEMON_NAME_MAP.put("Urshifu", "武道熊师");
        POKEMON_NAME_MAP.put("Zarude", "萨戮德");
        POKEMON_NAME_MAP.put("Regieleki", "雷吉艾勒奇");
        POKEMON_NAME_MAP.put("Regidrago", "雷吉铎拉戈");
        POKEMON_NAME_MAP.put("Glastrier", "雪暴马");
        POKEMON_NAME_MAP.put("Spectrier", "灵幽马");
        POKEMON_NAME_MAP.put("Calyrex", "蕾冠王");
        
        // 第九世代
        POKEMON_NAME_MAP.put("Sprigatito", "新叶喵");
        POKEMON_NAME_MAP.put("Floragato", "蒂蕾喵");
        POKEMON_NAME_MAP.put("Meowscarada", "魔幻假面喵");
        POKEMON_NAME_MAP.put("Fuecoco", "呆火鳄");
        POKEMON_NAME_MAP.put("Crocalor", "炙烫鳄");
        POKEMON_NAME_MAP.put("Skeledirge", "骨纹巨声鳄");
        POKEMON_NAME_MAP.put("Quaxly", "润水鸭");
        POKEMON_NAME_MAP.put("Quaxwell", "涌跃鸭");
        POKEMON_NAME_MAP.put("Quaquaval", "狂欢浪舞鸭");
        POKEMON_NAME_MAP.put("Koraidon", "故勒顿");
        POKEMON_NAME_MAP.put("Miraidon", "密勒顿");
        POKEMON_NAME_MAP.put("GreatTusk", "雄伟牙");
        POKEMON_NAME_MAP.put("BruteBonnet", "猛恶菇");
        POKEMON_NAME_MAP.put("FlutterMane", "振翼发");
        POKEMON_NAME_MAP.put("SlitherWing", "沙铁皮");
        POKEMON_NAME_MAP.put("SandyShocks", "沙螺蟒");
        POKEMON_NAME_MAP.put("RoaringMoon", "吼叫尾");
        POKEMON_NAME_MAP.put("IronTreads", "铁辙迹");
        POKEMON_NAME_MAP.put("IronBundle", "铁包袱");
        POKEMON_NAME_MAP.put("IronHands", "铁臂膀");
        POKEMON_NAME_MAP.put("IronJugulis", "铁脖颈");
        POKEMON_NAME_MAP.put("IronMoth", "铁毒蛾");
        POKEMON_NAME_MAP.put("IronThorns", "铁荆棘");
        POKEMON_NAME_MAP.put("Chien-Pao", "挈帕");
        POKEMON_NAME_MAP.put("Ting-Lu", "听途");
        POKEMON_NAME_MAP.put("Wo-Chien", "沃卡");
        POKEMON_NAME_MAP.put("Chi-Yu", "赤瑜");
    }

    // 默认宝可梦名称列表（英文，用于召唤）
    private static final List<String> DEFAULT_POKEMON_NAMES = Arrays.asList(
        // 第一世代
        "Bulbasaur", "Ivysaur", "Venusaur",
        "Charmander", "Charmeleon", "Charizard",
        "Squirtle", "Wartortle", "Blastoise",
        "Pikachu", "Raichu",
        "Mewtwo", "Mew",
        "Gengar", "Eevee", "Snorlax",
        "Dragonite", "Gyarados", "Lapras",
        
        // 第二世代
        "Chikorita", "Bayleef", "Meganium",
        "Cyndaquil", "Quilava", "Typhlosion",
        "Totodile", "Croconaw", "Feraligatr",
        "Ho-oh", "Lugia", "Raikou", "Entei", "Suicune", "Celebi",
        
        // 第三世代
        "Treecko", "Grovyle", "Sceptile",
        "Torchic", "Combusken", "Blaziken",
        "Mudkip", "Marshtomp", "Swampert",
        "Regirock", "Regice", "Registeel",
        "Latias", "Latios", "Groudon", "Kyogre", "Rayquaza",
        "Jirachi", "Deoxys",
        
        // 第四世代
        "Turtwig", "Grotle", "Torterra",
        "Chimchar", "Monferno", "Infernape",
        "Piplup", "Prinplup", "Empoleon",
        "Uxie", "Mesprit", "Azelf",
        "Dialga", "Palkia", "Heatran", "Regigigas", "Giratina",
        "Cresselia", "Phione", "Manaphy", "Darkrai", "Shaymin", "Arceus",
        
        // 第五世代
        "Snivy", "Servine", "Serperior",
        "Tepig", "Pignite", "Emboar",
        "Oshawott", "Dewott", "Samurott",
        "Victini", "Reshiram", "Zekrom", "Kyurem",
        "Cobalion", "Terrakion", "Virizion",
        "Tornadus", "Thundurus", "Landorus",
        "Keldeo", "Meloetta", "Genesect",
        
        // 第六世代
        "Chespin", "Quilladin", "Chesnaught",
        "Fennekin", "Braixen", "Delphox",
        "Froakie", "Frogadier", "Greninja",
        "Xerneas", "Yveltal", "Zygarde",
        "Diancie", "Hoopa", "Volcanion",
        
        // 第七世代
        "Rowlet", "Dartrix", "Decidueye",
        "Litten", "Torracat", "Incineroar",
        "Popplio", "Brionne", "Primarina",
        "Solgaleo", "Lunala", "Necrozma",
        "Nihilego", "Buzzwole", "Pheromosa", "Xurkitree", "Celesteela", "Kartana", "Guzzlord",
        "Magearna", "Marshadow",
        
        // 第八世代
        "Grookey", "Thwackey", "Rillaboom",
        "Scorbunny", "Raboot", "Cinderace",
        "Sobble", "Drizzile", "Inteleon",
        "Zacian", "Zamazenta", "Eternatus",
        "Kubfu", "Urshifu", "Zarude",
        "Regieleki", "Regidrago", "Glastrier", "Spectrier", "Calyrex",
        
        // 第九世代
        "Sprigatito", "Floragato", "Meowscarada",
        "Fuecoco", "Crocalor", "Skeledirge",
        "Quaxly", "Quaxwell", "Quaquaval",
        "Koraidon", "Miraidon",
        "GreatTusk", "BruteBonnet", "FlutterMane", "SlitherWing", "SandyShocks", "RoaringMoon",
        "IronTreads", "IronBundle", "IronHands", "IronJugulis", "IronMoth", "IronThorns",
        "Chien-Pao", "Ting-Lu", "Wo-Chien", "Chi-Yu"
    );

    public static void spawnRandom(Player player, Location loc) {
        try {
            // 从配置文件中读取宝可梦名称列表
            YamlConfiguration cfg = LuckyCobbleBlock.getInstance().getLuckyConfig().get();
            List<String> pokemonNames = cfg.getStringList("PokemonList");
            
            // 如果配置文件中没有宝可梦名称列表，使用默认列表
            if (pokemonNames.isEmpty()) {
                pokemonNames = DEFAULT_POKEMON_NAMES;
            }

            // 从配置文件中读取中文名称对照
            Map<String, String> nameMap = new HashMap<>(POKEMON_NAME_MAP);
            if (cfg.contains("PokemonNameMap")) {
                Object nameMapObj = cfg.get("PokemonNameMap");
                if (nameMapObj instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> configNameMap = (Map<String, Object>) nameMapObj;
                    for (Map.Entry<String, Object> entry : configNameMap.entrySet()) {
                        nameMap.put(entry.getKey(), entry.getValue().toString());
                    }
                }
            }

            // 随机选择一个宝可梦名称（英文）
            String englishName = pokemonNames.get(new Random().nextInt(pokemonNames.size()));
            // 获取对应的中文名称
            String chineseName = nameMap.getOrDefault(englishName, englishName);

            // 使用玩家执行指令，而不是控制台
            // 这样可以确保宝可梦在玩家附近生成，并且权限正确
            String command = "pokespawn " + englishName;
            player.performCommand(command);

            // 发送消息，使用中文名称显示
            String msg = "§a§l[幸运方块] §e" + player.getName() + " 开出了一只 §6" + chineseName + "§e！";
            player.getServer().broadcastMessage(msg);
            player.sendMessage("§a宝可梦生成成功！" + " 名称：" + chineseName);

        } catch (Exception e) {
            // 如果出错，显示错误信息
            player.sendMessage("§c召唤宝可梦失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
