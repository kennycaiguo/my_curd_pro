<#--用户信息-->
<#include "../common.ftl"/>
<@layout>
<style>
    .card table{
        width: 100%;
    }
    .card .avatar {
        width: 100px;
        border: 1px solid #eee;
        border-radius: 100%;
        margin: 0 auto;
        transition: .3s;
    }
    .card .avatar:hover{
        border:1px solid #fff;
        box-shadow: 0 0 10px #ffffff;
    }
    .card table.lsrm tr > td{
        font-size: 14px;
    }
</style>
    <#if sysUser??>
  <div class="card">
      <table class=" pure-table pure-table-horizontal labelInputTable lsrm" style="margin: 0 auto;">
          <tbody>
          <tr>
              <td colspan="2" style="background-color: #eee;">
             <#if (sysUser.gender)=='M' >
                 <img src="${ctx!}${(sysUser.avatar)!'/static/image/male.jpg'}" class="pure-img avatar"
                      alt="${(sysUser.username)!} avatar">
             </#if>
              <#if (sysUser.gender)=='F' >
                 <img src="${ctx!}${(sysUser.avatar)!'/static/image/female.jpg'}" class="pure-img avatar"
                      alt="${(sysUser.username)!} avatar">
              </#if>
              </td>
          </tr>
          <tr>
              <td>姓名：</td>
              <td>
                  ${(sysUser.name)!}
              </td>
          </tr>
          <tr>
              <td>性别：</td>
              <td>
                  ${(sysUser.GENDER_TEXT)!}
              </td>
          </tr>
          <tr >
              <td>邮箱：</td>
              <td>
                  ${(sysUser.email)!}
              </td>
          </tr>
          <tr>
              <td>电话：</td>
              <td>
                  ${(sysUser.phone)!}
              </td>
          </tr>
          <#if (sysUser.ORG_NAME)??>
          <tr>
              <td>部门：</td>
              <td>
                  ${(sysUser.ORG_NAME)!}
              </td>
          </tr>
          </#if>
          <#--
           <tr>
               <td>用户名：</td>
               <td >
                   ${(sysUser.username)!}
               </td>
           </tr>
           -->
          <tr>
              <td>职位：</td>
              <td>
                  ${(sysUser.job)!}
              </td>
          </tr>
          <tr>
              <td>级别：</td>
              <td>
                  ${(sysUser.JOB_LEVEL_TEXT)!}
              </td>
          </tr>
          <tr>
              <td>状态：</td>
              <td>
                  ${(sysUser.USER_STATE_TEXT)!}
              </td>
          </tr>
          </tbody>
      </table>
  </div>
    <#else>
        <div style="text-align: center;">
            找不到用户信息: ${(username)!}
        </div>
    </#if>
</@layout>
