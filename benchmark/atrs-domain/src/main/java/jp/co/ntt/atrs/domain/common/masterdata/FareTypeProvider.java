/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.atrs.domain.common.masterdata;

import jp.co.ntt.atrs.domain.model.FareType;
import jp.co.ntt.atrs.domain.model.FareTypeCd;
import jp.co.ntt.atrs.domain.repository.faretype.FareTypeRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * 運賃種別情報を提供するクラス。
 * @author NTT 電電太郎
 */
@Component
public class FareTypeProvider {

    /**
     * 運賃種別情報リポジトリ。
     */
    @Inject
    FareTypeRepository fareTypeRepository;

    /**
     * 運賃種別コードと運賃種別情報の関係を保持するマップ。
     */
    private final Map<FareTypeCd, FareType> fareTypeMap = new HashMap<>();

    /**
     * 運賃種別情報をロードし、キャッシュする。
     */
    @PostConstruct
    public void load() {
        List<FareType> fareTypeList = fareTypeRepository.findAll();
        for (FareType fareType : fareTypeList) {
            fareTypeMap.put(fareType.getFareTypeCd(), fareType);
        }
    }

    /**
     * 指定運賃種別コードに該当する運賃種別情報を取得。
     * @param fareTypeCd 運賃種別コード
     * @return 運賃種別情報。該当する運賃種別情報がない場合null。
     */
    public FareType getFareType(FareTypeCd fareTypeCd) {
        Assert.notNull(fareTypeCd);
        return fareTypeMap.get(fareTypeCd);
    }

}