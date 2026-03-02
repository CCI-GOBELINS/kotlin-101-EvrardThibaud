package com.android.one

enum class CharacterType(
    val baseHp: Int,
    val weaponName: String,
    val weaponPower: Int,
    val canHeal: Boolean
) {
    Warrior(baseHp = 120, weaponName = "Sword", weaponPower = 25, canHeal = false),
    Magus(baseHp = 140, weaponName = "Staff", weaponPower = 15, canHeal = true),
    Colossus(baseHp = 180, weaponName = "Hammer", weaponPower = 22, canHeal = false),
    Dwarf(baseHp = 90, weaponName = "Axe", weaponPower = 35, canHeal = false)
}

data class Weapon(val name: String, val power: Int)

class Character(val name: String, val type: CharacterType) {
    private var hp: Int = type.baseHp
    private val maxHp: Int = type.baseHp
    private val weapon: Weapon = Weapon(type.weaponName, type.weaponPower)

    fun isAlive(): Boolean = hp > 0
    fun currentHp(): Int = hp
    fun canHeal(): Boolean = type.canHeal

    fun attack(target: Character): Int {
        require(isAlive()) { "$name is dead and cannot attack." }
        require(target.isAlive()) { "${target.name} is already dead." }
        target.takeDamage(weapon.power)
        return weapon.power
    }

    fun heal(target: Character): Int {
        require(isAlive()) { "$name is dead and cannot heal." }
        require(canHeal()) { "$name cannot heal." }
        require(target.isAlive()) { "${target.name} is dead and cannot be healed." }
        val healAmount = weapon.power
        target.receiveHealing(healAmount)
        return healAmount
    }

    private fun takeDamage(amount: Int) {
        hp = (hp - amount).coerceAtLeast(0)
    }

    private fun receiveHealing(amount: Int) {
        hp = (hp + amount).coerceAtMost(maxHp)
    }

    fun statusLine(): String {
        val state = if (isAlive()) "ALIVE" else "DEAD"
        return "$name (${type.name}) - HP: $hp/$maxHp - $state"
    }
}

class Player(val name: String, val team: List<Character>) {
    fun aliveCharacters(): List<Character> = team.filter { it.isAlive() }
    fun isDefeated(): Boolean = aliveCharacters().isEmpty()
}

fun readNonBlank(prompt: String): String {
    while (true) {
        print(prompt)
        val value = readlnOrNull()?.trim().orEmpty()
        if (value.isNotBlank()) return value
        println("Input cannot be blank.")
    }
}

fun chooseIndex(prompt: String, maxExclusive: Int): Int {
    while (true) {
        print(prompt)
        val input = readlnOrNull()?.trim()
        val index = input?.toIntOrNull()
        if (index != null && index in 1..maxExclusive) return index - 1
        println("Please enter a number between 1 and $maxExclusive.")
    }
}

fun displayCharacters(title: String, characters: List<Character>) {
    println(title)
    characters.forEachIndexed { index, character ->
        println("${index + 1}. ${character.statusLine()}")
    }
}

fun chooseTypeForPlayer(availableTypes: MutableList<CharacterType>): CharacterType {
    while (true) {
        println("Choose a character type:")
        availableTypes.forEachIndexed { index, type ->
            val healText = if (type.canHeal) "yes" else "no"
            println(
                "${index + 1}. ${type.name} (HP ${type.baseHp}, weapon ${type.weaponName} " +
                    "power ${type.weaponPower}, can heal: $healText)"
            )
        }
        val selected = chooseIndex("Type number: ", availableTypes.size)
        return availableTypes.removeAt(selected)
    }
}

fun createTeam(playerName: String, usedNames: MutableSet<String>): List<Character> {
    println()
    println("=== Team creation for $playerName ===")
    val availableTypes = CharacterType.values().toMutableList()
    val team = mutableListOf<Character>()

    repeat(3) { slot ->
        println()
        println("Create character ${slot + 1}/3")
        val type = chooseTypeForPlayer(availableTypes)
        val name = while (true) {
            val candidate = readNonBlank("Choose a unique name: ")
            if (candidate in usedNames) {
                println("Name already used in this game. Choose another one.")
            } else {
                usedNames.add(candidate)
                break candidate
            }
        }
        team.add(Character(name = name, type = type))
        println("Created: ${team.last().statusLine()}")
    }
    return team
}

fun chooseCharacterFromList(prompt: String, candidates: List<Character>): Character {
    while (true) {
        displayCharacters(prompt, candidates)
        val selected = chooseIndex("Select number: ", candidates.size)
        return candidates[selected]
    }
}

fun doTurn(active: Player, enemy: Player) {
    println()
    println("----- ${active.name}'s turn -----")
    val attacker = chooseCharacterFromList("Choose your active character:", active.aliveCharacters())

    val canHeal = attacker.canHeal()
    val action = if (canHeal) {
        var choice = ""
        while (choice.isEmpty()) {
            print("Action? (1 attack, 2 heal): ")
            when (readlnOrNull()?.trim()) {
                "1" -> choice = "attack"
                "2" -> choice = "heal"
                else -> println("Invalid choice.")
            }
        }
        choice
    } else {
        "attack"
    }

    if (action == "attack") {
        val target = chooseCharacterFromList("Choose an enemy target:", enemy.aliveCharacters())
        val damage = attacker.attack(target)
        println("${attacker.name} attacks ${target.name} with ${damage} damage.")
        println("${target.name} now has ${target.currentHp()} HP.")
        if (!target.isAlive()) println("${target.name} has died.")
    } else {
        val target = chooseCharacterFromList("Choose an ally to heal:", active.aliveCharacters())
        val healAmount = attacker.heal(target)
        println("${attacker.name} heals ${target.name} for $healAmount HP.")
        println("${target.name} now has ${target.currentHp()} HP.")
    }
}

fun printFinalSummary(winner: Player, loser: Player, turns: Int) {
    println()
    println("===== GAME OVER =====")
    println("Winner: ${winner.name}")
    println("Turns played: $turns")
    println()
    displayCharacters("${winner.name} final team:", winner.team)
    println()
    displayCharacters("${loser.name} final team:", loser.team)
}

fun main() {
    println("Battle Arena - Console Prototype")
    val player1Name = readNonBlank("Player 1 name: ")
    val player2Name = readNonBlank("Player 2 name: ")

    val usedNames = mutableSetOf<String>()
    val player1 = Player(player1Name, createTeam(player1Name, usedNames))
    val player2 = Player(player2Name, createTeam(player2Name, usedNames))

    var turns = 0
    var active = player1
    var enemy = player2

    while (!player1.isDefeated() && !player2.isDefeated()) {
        doTurn(active, enemy)
        turns++
        val nextActive = enemy
        enemy = active
        active = nextActive
    }

    val winner = if (player1.isDefeated()) player2 else player1
    val loser = if (winner === player1) player2 else player1
    printFinalSummary(winner, loser, turns)
}
