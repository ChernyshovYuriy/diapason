package com.yuriy.diapason.analyzer

/**
 * Complete Fach definition including acoustic parameters AND musical context
 * (famous roles, example singers). The latter powers the Voice Types screen.
 */
data class FachDefinition(
    val name: String,
    val category: String,           // broad grouping label
    val rangeMinHz: Float,
    val rangeMaxHz: Float,
    val tessituraMinHz: Float,
    val tessituraMaxHz: Float,
    val passaggioHz: Float,
    val description: String,        // one-line character description
    val famousRoles: List<String>,  // opera / classical roles
    val exampleSingers: List<String>
)

data class VoiceProfile(
    val absoluteMinHz: Float,
    val absoluteMaxHz: Float,
    val tessituraLowHz: Float,
    val tessituraHighHz: Float,
    val estimatedPassaggioHz: Float,
    val sampleCount: Int,
    val durationSeconds: Float
)

data class FachMatch(
    val fach: FachDefinition,
    val score: Int,
    val maxScore: Int = 14,
    val scoreBreakdown: List<String>
)

// ─────────────────────────────────────────────────────────────────────────────
// Full Fach table
// ─────────────────────────────────────────────────────────────────────────────

val ALL_FACH: List<FachDefinition> = listOf(

    // ── SOPRANO ──────────────────────────────────────────────────────────────

    FachDefinition(
        name = "Coloratura Soprano",
        category = "Soprano",
        rangeMinHz = 262f, rangeMaxHz = 2093f,
        tessituraMinHz = 440f, tessituraMaxHz = 1319f,
        passaggioHz = 659f,
        description = "Extremely agile, stratospheric range, light and crystalline tone.",
        famousRoles = listOf(
            "Queen of the Night — The Magic Flute (Mozart)",
            "Lucia — Lucia di Lammermoor (Donizetti)",
            "Zerbinetta — Ariadne auf Naxos (R. Strauss)",
            "Olympia — The Tales of Hoffmann (Offenbach)"
        ),
        exampleSingers = listOf(
            "Diana Damrau",
            "Natalie Dessay",
            "Ruth Ann Swenson",
            "Edita Gruberová"
        )
    ),

    FachDefinition(
        name = "Lyric Coloratura Soprano",
        category = "Soprano",
        rangeMinHz = 262f, rangeMaxHz = 1397f,
        tessituraMinHz = 349f, tessituraMaxHz = 1047f,
        passaggioHz = 523f,
        description = "Brilliant upper range with warmth and expressive legato.",
        famousRoles = listOf(
            "Gilda — Rigoletto (Verdi)",
            "Norina — Don Pasquale (Donizetti)",
            "Rosina — The Barber of Seville (Rossini)",
            "Adina — L'elisir d'amore (Donizetti)"
        ),
        exampleSingers = listOf(
            "Cecilia Bartoli",
            "Sumi Jo",
            "Anna Netrebko (early career)",
            "Beverly Sills"
        )
    ),

    FachDefinition(
        name = "Lyric Soprano",
        category = "Soprano",
        rangeMinHz = 247f, rangeMaxHz = 1047f,
        tessituraMinHz = 294f, tessituraMaxHz = 880f,
        passaggioHz = 494f,
        description = "Warm, flowing, emotionally expressive — the most common soprano type.",
        famousRoles = listOf(
            "Mimì — La Bohème (Puccini)",
            "Tatyana — Eugene Onegin (Tchaikovsky)",
            "Countess Almaviva — The Marriage of Figaro (Mozart)",
            "Violetta — La Traviata (Verdi)"
        ),
        exampleSingers = listOf(
            "Renée Fleming",
            "Angela Gheorghiu",
            "Mirella Freni",
            "Ileana Cotrubaș"
        )
    ),

    FachDefinition(
        name = "Spinto Soprano",
        category = "Soprano",
        rangeMinHz = 233f, rangeMaxHz = 988f,
        tessituraMinHz = 277f, tessituraMaxHz = 784f,
        passaggioHz = 466f,
        description = "Lyric foundation pushed with dramatic intensity and darker colour.",
        famousRoles = listOf(
            "Tosca — Tosca (Puccini)",
            "Desdemona — Otello (Verdi)",
            "Leonora — Il Trovatore (Verdi)",
            "Aida — Aida (Verdi)"
        ),
        exampleSingers = listOf(
            "Renata Tebaldi",
            "Leontyne Price",
            "Montserrat Caballé",
            "Karita Mattila"
        )
    ),

    FachDefinition(
        name = "Dramatic Soprano",
        category = "Soprano",
        rangeMinHz = 220f, rangeMaxHz = 880f,
        tessituraMinHz = 262f, tessituraMaxHz = 698f,
        passaggioHz = 440f,
        description = "Powerful, heavy, penetrating tone built to fill large halls over big orchestras.",
        famousRoles = listOf(
            "Brünnhilde — The Ring Cycle (Wagner)",
            "Isolde — Tristan und Isolde (Wagner)",
            "Elektra — Elektra (R. Strauss)",
            "Turandot — Turandot (Puccini)"
        ),
        exampleSingers = listOf(
            "Birgit Nilsson",
            "Gwyneth Jones",
            "Nina Stemme",
            "Hildegard Behrens"
        )
    ),

    // ── MEZZO-SOPRANO ─────────────────────────────────────────────────────────

    FachDefinition(
        name = "Lyric Mezzo-Soprano",
        category = "Mezzo-Soprano",
        rangeMinHz = 196f, rangeMaxHz = 988f,
        tessituraMinHz = 247f, tessituraMaxHz = 740f,
        passaggioHz = 392f,
        description = "Rich mid-range with agility, often cast in trouser roles.",
        famousRoles = listOf(
            "Cherubino — The Marriage of Figaro (Mozart)",
            "Dorabella — Così fan tutte (Mozart)",
            "Rosina — The Barber of Seville (Rossini)",
            "Carmen — Carmen (Bizet, lighter interpretation)"
        ),
        exampleSingers = listOf(
            "Cecilia Bartoli",
            "Joyce DiDonato",
            "Susan Graham",
            "Frederica von Stade"
        )
    ),

    FachDefinition(
        name = "Dramatic Mezzo-Soprano",
        category = "Mezzo-Soprano",
        rangeMinHz = 175f, rangeMaxHz = 784f,
        tessituraMinHz = 220f, tessituraMaxHz = 622f,
        passaggioHz = 370f,
        description = "Dark, weighty, imposing — commands villains, witches and tragic mothers.",
        famousRoles = listOf(
            "Carmen — Carmen (Bizet)",
            "Amneris — Aida (Verdi)",
            "Azucena — Il Trovatore (Verdi)",
            "Kundry — Parsifal (Wagner)"
        ),
        exampleSingers = listOf(
            "Shirley Verrett",
            "Grace Bumbry",
            "Olga Borodina",
            "Fiorenza Cossotto"
        )
    ),

    FachDefinition(
        name = "Contralto",
        category = "Contralto",
        rangeMinHz = 165f, rangeMaxHz = 698f,
        tessituraMinHz = 196f, tessituraMaxHz = 523f,
        passaggioHz = 330f,
        description = "The rarest female voice — earthy, resonant, profound low register.",
        famousRoles = listOf(
            "Erda — Das Rheingold (Wagner)",
            "Ulrica — Un Ballo in Maschera (Verdi)",
            "Mistress Quickly — Falstaff (Verdi)",
            "La Cieca — La Gioconda (Ponchielli)"
        ),
        exampleSingers = listOf(
            "Marian Anderson",
            "Kathleen Ferrier",
            "Ewa Podleś",
            "Nathalie Stutzmann"
        )
    ),

    // ── TENOR ─────────────────────────────────────────────────────────────────

    FachDefinition(
        name = "Countertenor",
        category = "Countertenor",
        rangeMinHz = 165f, rangeMaxHz = 880f,
        tessituraMinHz = 220f, tessituraMaxHz = 659f,
        passaggioHz = 330f,
        description = "Male voice using falsetto/head voice in the alto–mezzo range, specialising in Baroque.",
        famousRoles = listOf(
            "Julius Caesar — Giulio Cesare (Handel)",
            "Oberon — A Midsummer Night's Dream (Britten)",
            "Ottone — L'incoronazione di Poppea (Monteverdi)",
            "Bertarido — Rodelinda (Handel)"
        ),
        exampleSingers = listOf(
            "Andreas Scholl",
            "David Daniels",
            "Philippe Jaroussky",
            "Alfred Deller"
        )
    ),

    FachDefinition(
        name = "Lyric Tenor",
        category = "Tenor",
        rangeMinHz = 130f, rangeMaxHz = 523f,
        tessituraMinHz = 196f, tessituraMaxHz = 440f,
        passaggioHz = 311f,
        description = "Bright, sweet, elegant — the quintessential romantic lead.",
        famousRoles = listOf(
            "Tamino — The Magic Flute (Mozart)",
            "Alfredo — La Traviata (Verdi)",
            "Rodolfo — La Bohème (Puccini)",
            "Don Ottavio — Don Giovanni (Mozart)"
        ),
        exampleSingers = listOf(
            "Nicolai Gedda",
            "Fritz Wunderlich",
            "Rolando Villazón",
            "Juan Diego Flórez"
        )
    ),

    FachDefinition(
        name = "Spinto Tenor",
        category = "Tenor",
        rangeMinHz = 123f, rangeMaxHz = 494f,
        tessituraMinHz = 175f, tessituraMaxHz = 415f,
        passaggioHz = 294f,
        description = "Lyric core with dramatic edge — can sustain powerful high notes.",
        famousRoles = listOf(
            "Cavaradossi — Tosca (Puccini)",
            "Don Alvaro — La Forza del Destino (Verdi)",
            "Radamès — Aida (Verdi)",
            "Manrico — Il Trovatore (Verdi)"
        ),
        exampleSingers = listOf(
            "Plácido Domingo",
            "José Carreras",
            "Carlo Bergonzi",
            "Jussi Björling"
        )
    ),

    FachDefinition(
        name = "Dramatic Tenor (Heldentenor)",
        category = "Tenor",
        rangeMinHz = 110f, rangeMaxHz = 466f,
        tessituraMinHz = 165f, tessituraMaxHz = 392f,
        passaggioHz = 277f,
        description = "Massive, heroic, built for Wagner — endurance over agility.",
        famousRoles = listOf(
            "Siegfried — Siegfried / Götterdämmerung (Wagner)",
            "Tristan — Tristan und Isolde (Wagner)",
            "Tannhäuser — Tannhäuser (Wagner)",
            "Parsifal — Parsifal (Wagner)"
        ),
        exampleSingers = listOf(
            "Lauritz Melchior",
            "Wolfgang Windgassen",
            "Ben Heppner",
            "Jon Vickers"
        )
    ),

    // ── BARITONE ──────────────────────────────────────────────────────────────

    FachDefinition(
        name = "Lyric Baritone",
        category = "Baritone",
        rangeMinHz = 110f, rangeMaxHz = 392f,
        tessituraMinHz = 147f, tessituraMaxHz = 330f,
        passaggioHz = 220f,
        description = "Smooth, warm, noble — charming leading men and romantic anti-heroes.",
        famousRoles = listOf(
            "Papageno — The Magic Flute (Mozart)",
            "Count Almaviva — The Marriage of Figaro (Mozart)",
            "Marcello — La Bohème (Puccini)",
            "Valentin — Faust (Gounod)"
        ),
        exampleSingers = listOf(
            "Hermann Prey",
            "Dietrich Fischer-Dieskau",
            "Thomas Allen",
            "Bo Skovhus"
        )
    ),

    FachDefinition(
        name = "Kavalierbariton",
        category = "Baritone",
        rangeMinHz = 98f, rangeMaxHz = 370f,
        tessituraMinHz = 138f, tessituraMaxHz = 311f,
        passaggioHz = 207f,
        description = "The elegant, aristocratic baritone — authoritative yet refined.",
        famousRoles = listOf(
            "Don Giovanni — Don Giovanni (Mozart)",
            "Onegin — Eugene Onegin (Tchaikovsky)",
            "Rodrigo — Don Carlos (Verdi)",
            "Hans Sachs — Die Meistersinger (Wagner, lighter cast)"
        ),
        exampleSingers = listOf(
            "Eberhard Wächter",
            "Simon Keenlyside",
            "Dmitri Hvorostovsky",
            "Matthias Goerne"
        )
    ),

    FachDefinition(
        name = "Dramatic Baritone",
        category = "Baritone",
        rangeMinHz = 87f, rangeMaxHz = 349f,
        tessituraMinHz = 123f, tessituraMaxHz = 294f,
        passaggioHz = 196f,
        description = "Dark, powerful, intense — the archetypal Verdi villain and tragic father.",
        famousRoles = listOf(
            "Rigoletto — Rigoletto (Verdi)",
            "Iago — Otello (Verdi)",
            "Scarpia — Tosca (Puccini)",
            "Macbeth — Macbeth (Verdi)"
        ),
        exampleSingers = listOf("Tito Gobbi", "Leonard Warren", "Cornell MacNeil", "Leo Nucci")
    ),

    FachDefinition(
        name = "Bass-Baritone",
        category = "Bass",
        rangeMinHz = 82f, rangeMaxHz = 330f,
        tessituraMinHz = 110f, tessituraMaxHz = 277f,
        passaggioHz = 185f,
        description = "Bridges bass depth with baritone agility — ideal for Wagner's heroic villains.",
        famousRoles = listOf(
            "Wotan — The Ring Cycle (Wagner)",
            "Hans Sachs — Die Meistersinger (Wagner)",
            "Amfortas — Parsifal (Wagner)",
            "Don Pizarro — Fidelio (Beethoven)"
        ),
        exampleSingers = listOf(
            "James Morris",
            "John Tomlinson",
            "René Pape (early)",
            "Franz Ferdinand Nentwig"
        )
    ),

    // ── BASS ─────────────────────────────────────────────────────────────────

    FachDefinition(
        name = "Basso Cantante",
        category = "Bass",
        rangeMinHz = 73f, rangeMaxHz = 330f,
        tessituraMinHz = 98f, tessituraMaxHz = 262f,
        passaggioHz = 175f,
        description = "Singing bass — warm, lyrical, legato; wise kings and benevolent fathers.",
        famousRoles = listOf(
            "Sarastro — The Magic Flute (Mozart)",
            "King Philip II — Don Carlos (Verdi)",
            "Gurnemanz — Parsifal (Wagner)",
            "Banco — Macbeth (Verdi)"
        ),
        exampleSingers = listOf(
            "Boris Christoff",
            "Nicolai Ghiaurov",
            "René Pape",
            "Ferruccio Furlanetto"
        )
    ),

    FachDefinition(
        name = "Basso Profundo",
        category = "Bass",
        rangeMinHz = 65f, rangeMaxHz = 294f,
        tessituraMinHz = 82f, tessituraMaxHz = 220f,
        passaggioHz = 155f,
        description = "The deepest orchestral voice — imposing, cavernous, thunderous low notes.",
        famousRoles = listOf(
            "Méphistophélès — Faust (Gounod)",
            "Boris Godunov — Boris Godunov (Mussorgsky)",
            "Hagen — Götterdämmerung (Wagner)",
            "Grand Inquisitor — Don Carlos (Verdi)"
        ),
        exampleSingers = listOf("Martti Talvela", "Kurt Moll", "Matti Salminen", "Alexander Kipnis")
    ),

    FachDefinition(
        name = "Contrabass (Oktavist)",
        category = "Bass",
        rangeMinHz = 43f, rangeMaxHz = 220f,
        tessituraMinHz = 65f, tessituraMaxHz = 165f,
        passaggioHz = 130f,
        description = "Extraordinarily rare — sub-bass specialists in Russian Orthodox choral tradition.",
        famousRoles = listOf(
            "Russian Orthodox liturgical chant (specialist role)",
            "Certain Russian opera choral parts (Mussorgsky, Rimsky-Korsakov)",
            "Subdeacon roles in religious music"
        ),
        exampleSingers = listOf(
            "Tim Storms (world record holder)",
            "Vladimir Paschchenko",
            "Leonid Kharitonov"
        )
    )
)
