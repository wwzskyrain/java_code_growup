package jdk.util.datastructure.tree.vo;


import java.util.List;

public class Category {

    private Integer categoryId;
    private String name;
    private Integer status;
    private List<Category> children;
    private Integer parentId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "autoIncreaseCategoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", children=" + children +
                ", parentId=" + parentId +
                '}';
    }

    public void print(){
        System.out.printf("[categoryId:%d parentId:%d name:%s status:%s]\n",categoryId,parentId,name,status);
    }

}
