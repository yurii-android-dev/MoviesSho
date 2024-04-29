package com.yuriishcherbyna.moviessho.util


fun List<Int>.toGenresText(): List<String> {
    val genreIdToText = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )

    return this.filter { genreId -> genreIdToText.containsKey(genreId) }
        .map { genreId -> genreIdToText[genreId]!! }
}

fun Int.toPrettyFormattedTime(): String {
    val hours = this / 60
    val minutes = this % 60

    return if (minutes == 0) "${hours}h" else "${hours}h ${minutes}min"
}

fun String.toFullLanguage(): String {
    return when (this) {
        "aa" -> "Afar"
        "ab" -> "Abkhazian"
        "af" -> "Afrikaans"
        "am" -> "Amharic"
        "ar" -> "Arabic"
        "as" -> "Assamese"
        "ay" -> "Aymara"
        "az" -> "Azerbaijani"
        "ba" -> "Bashkir"
        "be" -> "Byelorussian"
        "bg" -> "Bulgarian"
        "bh" -> "Bihari"
        "bi" -> "Bislama"
        "bn" -> "Bengali"
        "bo" -> "Tibetan"
        "br" -> "Breton"
        "ca" -> "Catalan"
        "co" -> "Corsican"
        "cs" -> "Czech"
        "cy" -> "Welch"
        "da" -> "Danish"
        "de" -> "German"
        "dz" -> "Bhutani"
        "el" -> "Greek"
        "en" -> "English"
        "eo" -> "Esperanto"
        "es" -> "Spanish"
        "et" -> "Estonian"
        "eu" -> "Basque"
        "fa" -> "Persian"
        "fi" -> "Finnish"
        "fj" -> "Fiji"
        "fo" -> "Faeroese"
        "fr" -> "French"
        "fy" -> "Frisian"
        "ga" -> "Irish"
        "gd" -> "Scots Gaelic"
        "gl" -> "Galician"
        "gn" -> "Guarani"
        "gu" -> "Gujarati"
        "ha" -> "Hausa"
        "hi" -> "Hindi"
        "he" -> "Hebrew"
        "hr" -> "Croatian"
        "hu" -> "Hungarian"
        "hy" -> "Armenian"
        "ia" -> "Interlingua"
        "id" -> "Indonesian"
        "ie" -> "Interlingue"
        "ik" -> "Inupiak"
        "in" -> "former Indonesian"
        "is" -> "Icelandic"
        "it" -> "Italian"
        "iu" -> "Inuktitut (Eskimo)"
        "iw" -> "former Hebrew"
        "ja" -> "Japanese"
        "ji" -> "former Yiddish"
        "jw" -> "Javanese"
        "ka" -> "Georgian"
        "kk" -> "Kazakh"
        "kl" -> "Greenlandic"
        "km" -> "Cambodian"
        "kn" -> "Kannada"
        "ko" -> "Korean"
        "ks" -> "Kashmiri"
        "ku" -> "Kurdish"
        "ky" -> "Kirghiz"
        "la" -> "Latin"
        "ln" -> "Lingala"
        "lo" -> "Laothian"
        "lt" -> "Lithuanian"
        "lv" -> "Latvian"
        "mg" -> "Malagasy"
        "mi" -> "Maori"
        "mk" -> "Macedonian"
        "ml" -> "Malayalam"
        "mn" -> "Mongolian"
        "mo" -> "Moldavian"
        "mr" -> "Marathi"
        "ms" -> "Malay"
        "mt" -> "Maltese"
        "my" -> "Burmese"
        "na" -> "Nauru"
        "ne" -> "Nepali"
        "nl" -> "Dutch"
        "no" -> "Norwegian"
        "oc" -> "Occitan"
        "om" -> "Afan"
        "or" -> "Oriya"
        "pa" -> "Punjabi"
        "pl" -> "Polish"
        "ps" -> "Pashto"
        "pt" -> "Portuguese"
        "qu" -> "Quechua"
        "rm" -> "Rhaeto-Romance"
        "rn" -> "Kirundi"
        "ro" -> "Romanian"
        "ru" -> "Russian"
        "rw" -> "Kinyarwanda"
        "sa" -> "Sanskrit"
        "sd" -> "Sindhi"
        "sg" -> "Sangro"
        "sh" -> "Serbo-Croatian"
        "si" -> "Singhalese"
        "sk" -> "Slovak"
        "sl" -> "Slovenian"
        "sm" -> "Samoan"
        "sn" -> "Shona"
        "so" -> "Somali"
        "sq" -> "Albanian"
        "sr" -> "Serbian"
        "ss" -> "Siswati"
        "st" -> "Sesotho"
        "su" -> "Sudanese"
        "sv" -> "Swedish"
        "sw" -> "Swahili"
        "ta" -> "Tamil"
        "te" -> "Tegulu"
        "tg" -> "Tajik"
        "th" -> "Thai"
        "ti" -> "Tigrinya"
        "tk" -> "Turkmen"
        "tl" -> "Tagalog"
        "tn" -> "Setswana"
        "to" -> "Tonga"
        "tr" -> "Turkish"
        "ts" -> "Tsonga"
        "tt" -> "Tatar"
        "tw" -> "Twi"
        "ug" -> "Uigur"
        "uk" -> "Ukrainian"
        "ur" -> "Urdu"
        "uz" -> "Uzbek"
        "vi" -> "Vietnamese"
        "vo" -> "Volapuk"
        "wo" -> "Wolof"
        "xh" -> "Xhosa"
        "yi" -> "Yiddish"
        "yo" -> "Yoruba"
        "za" -> "Zhuang"
        "zh" -> "Chinese"
        "zu" -> "Zulu"
        else -> ""
    }
}