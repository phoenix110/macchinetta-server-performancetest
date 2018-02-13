/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.atrs.domain.service.b2;

import jp.co.ntt.atrs.domain.model.Flight;
import jp.co.ntt.atrs.domain.model.Member;
import jp.co.ntt.atrs.domain.model.Passenger;
import jp.co.ntt.atrs.domain.model.Reservation;

import org.terasoluna.gfw.common.exception.BusinessException;

import java.util.List;

/**
 * チケット予約のサービスインターフェース。
 * @author NTT 電電三郎
 */
public interface TicketReserveService {

    /**
     * 予約情報の業務ロジックチェックを行う。
     * @param reservation 予約情報
     * @throws BusinessException チェックに引っかかった場合
     */
    void validateReservation(Reservation reservation) throws BusinessException;

    /**
     * 予約チケットの合計金額を計算する。
     * @param flightList 予約するフライトのリスト
     * @param passengerList 搭乗者リスト
     * @return 予約チケットの合計金額
     */
    int calculateTotalFare(List<Flight> flightList,
            List<Passenger> passengerList);

    /**
     * 予約情報をDBに登録し、予約したチケット料金の支払期限を決定する。
     * @param reservation 予約情報
     * @return 予約番号と予約したチケット料金の支払期限
     * @throws BusinessException 空席数が搭乗者数未満の場合にスローする例外
     */
    TicketReserveDto registerReservation(
            Reservation reservation) throws BusinessException;

    /**
     * お客様番号に該当するカード会員情報を検索する。
     * @param customerNo お客様番号
     * @return カード会員情報
     */
    Member findMember(String customerNo);

}
