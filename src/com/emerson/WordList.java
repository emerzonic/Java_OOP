package com.emerson;

 class WordList {
   private static String wordsList[] = new String[]{"consider", "minute", "accord", "evident", "practice", "intend", "concern", "commit",
           "issue", "approach", "establish", "utter", "conduct", "engage", "obtain", "scarce", "policy", "straight",
           "stock", "apparent", "property", "fancy", "concept", "court", "appoint", "passage", "vain", "instance",
           "coast", "project", "commission", "constant", "circumstances", "constitute", "level", "affect", "institute",
           "render", "appeal", "generate", "theory", "range", "campaign", "league", "labor", "confer", "grant", "dwell",
           "entertain", "contract", "earnest", "yield", "wander", "insist", "knight", "convince", "inspire",
           "convention", "skill", "harry", "financial", "reflect", "novel", "furnish", "compel", "venture",
           "territory", "temper", "bent", "intimate", "undertake", "majority", "assert", "crew", "chamber",
           "humble", "scheme", "keen", "liberal", "despair", "tide", "attitude", "justify", "flag", "merit",
           "manifest", "notion", "scale", "formal", "resource", "persist", "contempt", "tour", "plead", "weigh",
           "mode", "distinction", "inclined", "attribute", "exert"};


     static String getRandomWord() {
        return  wordsList[(int) (Math.random() * wordsList.length)];
    }
}
