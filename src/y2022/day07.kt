package y2022

import readFileAsLines

enum class EntryType {
    DIRECTORY,
    FILE,
}

sealed interface Entry {
    val type: EntryType
    val name: String

    data class FileEntry(
        val size: Int,
        override val name: String,
        override val type: EntryType = EntryType.FILE
    ) : Entry

    data class DirectoryEntry(
        override val name: String,
        override val type: EntryType = EntryType.DIRECTORY
    ) : Entry
}

class TreeNode(
    val parent: TreeNode? = null,
    val value: Entry,
) {
    val children: MutableList<TreeNode> = mutableListOf()

    fun getChild(name: String): TreeNode {
        val entry = children.first {
            it.value.name == name
        }
        return entry
    }

    fun addChild(child: TreeNode) {
        children.add(child)
    }

    fun prettyString(): String {
        val stringBuilder = StringBuilder()
        print(stringBuilder, "", "")
        return stringBuilder.toString()
    }

    private fun print(stringBuilder: StringBuilder, prefix: String, childrenPrefix: String) {
        stringBuilder.append(prefix)
        stringBuilder.append(value)
        stringBuilder.append('\n')
        val childIterator = children.iterator()
        while (childIterator.hasNext()) {
            val node = childIterator.next()
            if (childIterator.hasNext()) {
                node.print(stringBuilder, "$childrenPrefix├── ", "$childrenPrefix│   ")
            } else {
                node.print(stringBuilder, "$childrenPrefix└── ", "$childrenPrefix    ")
            }
        }
    }
}

data class ToBeSummed(
    val path: String,
    val size: Int,
)

fun TreeNode.part1(currentPath: String, list: MutableList<ToBeSummed>): Int {
    if (value.type == EntryType.FILE) return (value as Entry.FileEntry).size

    val total = children
        .map { it.part1(currentPath + "/${it.value.name}", list) }
        .sumOf { it }

    if (total <= 100_000) {
        list.add(ToBeSummed(currentPath, total))
    }

    return total
}

fun TreeNode.part2(currentPath: String, list: MutableList<ToBeSummed>): Int {
    when (value.type) {
        EntryType.FILE -> {
//            println("File name: $currentPath, size: ${(value as Entry.FileEntry).size})")
            return (value as Entry.FileEntry).size
        }

        else -> Unit //println("Directory name: $currentPath")
    }

    val total = children
        .map {
            it.part2(currentPath + "/${it.value.name}", list)
        }
        .sumOf { it }

    list.add(ToBeSummed(currentPath, total))
    return total
}


fun main() {
    fun populateTree(input: List<String>, currentNode: TreeNode) {
        var currentNode1 = currentNode
        input.forEach { line ->
            val splitLine = line.split(" ")
            if (splitLine.first() == "$") {
                val splitLine = line.split(" ")
                val prompt = splitLine.first() // $
                val command = splitLine[1]
                val directory = if (command == "cd") splitLine[2] else ""
                if (command == "cd") {
                    if (directory == "..")
                        currentNode1 = currentNode1.parent!! // YAY!!!
                    else if (directory == "/")
                    // nothing
                    else
                        currentNode1 = currentNode1.getChild(directory)
                }
            } else {
                if (splitLine.first() == "dir") {
                    // add a new DIRECTORY node
                    val directoryName = splitLine.last()
                    currentNode1.addChild(
                        TreeNode(
                            parent = currentNode1,
                            value = Entry.DirectoryEntry(name = directoryName),
                        )
                    )
                } else {
                    currentNode1.addChild(
                        TreeNode(
                            parent = currentNode1,
                            value = Entry.FileEntry(
                                size = splitLine.first().toInt(),
                                name = splitLine.last()
                            )
                        )
                    )
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val tree = TreeNode(value = Entry.DirectoryEntry("/"))
        populateTree(input, tree)

        println(tree.prettyString())

        val listDirectoriesLessThanSize = mutableListOf<ToBeSummed>()
        tree.part1("", listDirectoriesLessThanSize)
        return listDirectoriesLessThanSize.sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        val tree = TreeNode(value = Entry.DirectoryEntry("/"))
        populateTree(input, tree)

        val allDirectories = mutableListOf<ToBeSummed>()
        tree.part2("", allDirectories)

        val usedSpace = allDirectories.find { it.path.isEmpty() }?.size ?: 0
        val neededSpace = 70_000_000 - usedSpace

        allDirectories.remove(allDirectories.find { it.path.isEmpty() })

        val duplicateDirectories = mutableListOf<ToBeSummed>()

        allDirectories.sortBy { it.path.length }
        allDirectories
            .forEachIndexed { itemIndex, item ->
                allDirectories
                    .filterIndexed { filterIndex, filterItem ->
                        item.path.contains(filterItem.path) && itemIndex != filterIndex
                    }
                    .onEach { duplicate ->
                        if (duplicate.size == item.size) {
                            allDirectories
                                .find { it == duplicate }
                                ?.let { duplicateDirectories.add(it) }
                        }
                    }
            }

        duplicateDirectories.forEach {
            allDirectories.remove(it)
        }

        allDirectories.sortByDescending { it.size }

        val item = allDirectories
            .filter {
                (it.size + neededSpace) > 30_000_000
            }
            .reversed()
            .first()

        return item.size
    }

    "y2022/data/day07_test".readFileAsLines().let { input ->
        check(part1(input) == 95437)
        check(part2(input) == 24933642)
    }

    "y2022/data/day07".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
