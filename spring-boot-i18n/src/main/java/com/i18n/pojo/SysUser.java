package com.i18n.pojo;

public class SysUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.id
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.name
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.login_name
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.password
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.email
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    private String email;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.id
     *
     * @return the value of sys_user.id
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.id
     *
     * @param id the value for sys_user.id
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.name
     *
     * @return the value of sys_user.name
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.name
     *
     * @param name the value for sys_user.name
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.login_name
     *
     * @return the value of sys_user.login_name
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.login_name
     *
     * @param loginName the value for sys_user.login_name
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.password
     *
     * @return the value of sys_user.password
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.password
     *
     * @param password the value for sys_user.password
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.email
     *
     * @return the value of sys_user.email
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.email
     *
     * @param email the value for sys_user.email
     *
     * @mbggenerated Fri Mar 23 15:29:26 CST 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}