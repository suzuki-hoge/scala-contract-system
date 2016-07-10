package domain.member

case class Name(kanji: Kanji, kana: Kana)

case class Kanji(s: String)

case class Kana(s: String)
