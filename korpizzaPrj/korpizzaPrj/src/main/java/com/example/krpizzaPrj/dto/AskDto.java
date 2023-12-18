package com.example.krpizzaPrj.dto;

public class AskDto {
    private int id;
    private String optionst;
    private String subject;
    private String writer;
    private String content;
    private  int visit;
    private String regdate;
    private String orgName;
    private String savedFileName;
    private String savedFilePathName;
    private long savedFileSize;
    private int grp;
    private int seq;
    private int depth;
    private String folderName;
    private String ext;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionst() {
        return optionst;
    }

    public void setOptionst(String optionst) {
        this.optionst = optionst;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getSavedFilePathName() {
        return savedFilePathName;
    }

    public void setSavedFilePathName(String savedFilePathName) {
        this.savedFilePathName = savedFilePathName;
    }

    public long getSavedFileSize() {
        return savedFileSize;
    }

    public void setSavedFileSize(long savedFileSize) {
        this.savedFileSize = savedFileSize;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "AskDto{" +
                "id=" + id +
                ", optionst='" + optionst + '\'' +
                ", subject='" + subject + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", visit=" + visit +
                ", regdate='" + regdate + '\'' +
                ", orgName='" + orgName + '\'' +
                ", savedFileName='" + savedFileName + '\'' +
                ", savedFilePathName='" + savedFilePathName + '\'' +
                ", savedFileSize=" + savedFileSize +
                ", grp=" + grp +
                ", seq=" + seq +
                ", depth=" + depth +
                ", folderName='" + folderName + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
