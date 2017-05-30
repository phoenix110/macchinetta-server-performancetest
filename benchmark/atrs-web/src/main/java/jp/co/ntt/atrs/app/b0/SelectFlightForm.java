/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.atrs.app.b0;

import jp.co.ntt.atrs.domain.model.BoardingClassCd;
import jp.co.ntt.atrs.domain.model.FareTypeCd;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 選択フライト情報フォーム。
 * <p>
 * 空席照会結果から選択されたフライトの情報を受け取る。
 * </p>
 * @author NTT 電電次郎
 */
public class SelectFlightForm implements Serializable {

    /**
     * シリアルバージョンUID。
     */
    private static final long serialVersionUID = 2564839849286457368L;

    /**
     * 搭乗日。
     */
    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private Date departureDate;

    /**
     * 便名。
     */
    @NotNull
    private String flightName;

    /**
     * 搭乗クラスコード。
     */
    @NotNull
    private BoardingClassCd boardingClassCd;

    /**
     * 運賃種別コード。
     */
    @NotNull
    private FareTypeCd fareTypeCd;

    /**
     * 搭乗日を取得する。
     * @return 搭乗日
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * 搭乗日を設定する。
     * @param departureDate 搭乗日
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * 便名を取得する。
     * @return 便名
     */
    public String getFlightName() {
        return flightName;
    }

    /**
     * 便名を設定する。
     * @param flightName 便名
     */
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    /**
     * 搭乗クラスコードを取得する。
     * @return 搭乗クラスコード
     */
    public BoardingClassCd getBoardingClassCd() {
        return boardingClassCd;
    }

    /**
     * 搭乗クラスコードを設定する。
     * @param boardingClassCd 搭乗クラスコード
     */
    public void setBoardingClassCd(BoardingClassCd boardingClassCd) {
        this.boardingClassCd = boardingClassCd;
    }

    /**
     * 運賃種別コードを取得する。
     * @return 運賃種別コード
     */
    public FareTypeCd getFareTypeCd() {
        return fareTypeCd;
    }

    /**
     * 運賃種別コードを設定する。
     * @param fareTypeCd 運賃種別コード
     */
    public void setFareTypeCd(FareTypeCd fareTypeCd) {
        this.fareTypeCd = fareTypeCd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}