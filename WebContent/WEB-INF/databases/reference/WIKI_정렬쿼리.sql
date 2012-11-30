SELECT T2.RNUM 
,      T1.WE_WIKI_IDX
,      T1. WE_WIKI_PARENT_IDX 
,      concat(lpad(' ' , (case T1.WE_WIKI_ORDER_IDX when 0 then 0 
                                                    else T1.WE_WIKI_ORDER_IDX * 2 end) ,' ') , T1.WE_WIKI_TITLE) title
FROM
    (SELECT 
        we_wiki_idx,
        we_space_idx,
        we_wiki_parent_idx,
        we_wiki_order_idx,
        we_wiki_depth_idx,
        we_wiki_title,
        we_wiki_text,
        we_wiki_markup,
        we_wiki_revision,
        we_wiki_status,
        we_wiki_quota,
        we_wiki_url,
        we_wiki_agree,
        we_wiki_view_cnt,
        we_wiki_prev,
        we_wiki_next,
        we_user_ip,
        we_wiki_protect,
        we_edit_text,
        we_use_yn,
        we_edit_yn,
        we_ins_user,
        we_ins_date,
        we_upd_user,
        we_upd_date
    FROM
        WE_WIKI
    WHERE we_space_idx = 1 
    AND we_use_yn = 'Y'
    ) T1
   LEFT OUTER JOIN
    (SELECT @RNUM := @RNUM + 1 RNUM
    ,      WE_WIKI_PARENT_IDX, WE_WIKI_ORDER_IDX
    ,      WE_WIKI_TITLE
    FROM WE_WIKI T1,
        (SELECT @RNUM := 0 FROM DUAL) T2
    WHERE T1.we_space_idx = 1
    AND T1.we_use_yn = 'Y'
    AND T1.WE_WIKI_DEPTH_IDX =0
    ORDER BY T1.WE_WIKI_TITLE
    ) T2
   ON T1. WE_WIKI_PARENT_IDX = T2.WE_WIKI_PARENT_IDX
ORDER BY T2.RNUM ASC, T1. WE_WIKI_PARENT_IDX ASC ,T1.WE_WIKI_ORDER_IDX ASC;
