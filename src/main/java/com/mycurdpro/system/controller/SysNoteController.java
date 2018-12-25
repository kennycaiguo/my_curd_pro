package com.mycurdpro.system.controller;

import com.mycurdpro.common.base.BaseController;

/**
 * 个人笔记
 */
public class SysNoteController extends BaseController {
    public void  index(){
        redirect("https://note.youdao.com");
    }
}
