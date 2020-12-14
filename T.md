```java
class T {

    Node getNode(Integer rootFileId) {
        Node node = new Node(rootFileId, true, new ArrayList<>(), null);
        Map<Integer, Node> idMap = new HashMap<>();
        idMap.put(rootFileId, node);

        Map<Integer, List<DirEntity>> map = new HashMap<>();

        for (DirEntity dirEntity : findAllDirEntity()) {
            map.putIfAbsent(dirEntity.parentId, new ArrayList<>());
            map.get(dirEntity.parentId).add(dirEntity);
        }
        h(node, map, idMap);

        for (FileEntity fileEntity : findAllFileEntity())
            idMap.get(fileEntity.dirId).sonNodes.add(new Node(fileEntity.id, false, null, fileEntity.Data));

        return node;
    }

    void h(Node f, Map<Integer, List<DirEntity>> map, Map<Integer, Node> idMap) {
        if (!map.containsKey(f.id)) return;
        List<DirEntity> sonDirs = map.get(f.id);
        for (DirEntity sonDir : sonDirs) {
            Node fileNode = new Node(sonDir.id, true, new ArrayList<>(), null);
            idMap.put(fileNode.id, fileNode);
            f.sonNodes.add(fileNode);
            h(fileNode, map, idMap);
        }
    }

    //mock
    List<DirEntity> findAllDirEntity() {
        return new ArrayList<>();
    }

    //mock
    List<FileEntity> findAllFileEntity() {
        return new ArrayList<>();
    }
}

class DirEntity {
    int id;
    int parentId;
}

class FileEntity {
    int id;
    int dirId;
    Object Data;
}

class Node {
    int id;
    boolean isDir;
    List<Node> sonNodes;
    Object fileData;

    public Node(int id, boolean isDir, List<Node> sonNodes, Object fileData) {
        this.id = id;
        this.isDir = isDir;
        this.sonNodes = sonNodes;
        this.fileData = fileData;
    }
}
```

