/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.atrs.domain.service.c1;

import jp.co.ntt.atrs.domain.common.logging.LogMessages;
import jp.co.ntt.atrs.domain.model.Member;
import jp.co.ntt.atrs.domain.model.MemberLogin;
import jp.co.ntt.atrs.domain.repository.member.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.terasoluna.gfw.common.exception.SystemException;

import javax.inject.Inject;

/**
 * 会員情報登録を行うService実装クラス。
 * @author NTT 電電花子
 */
@Service
@Transactional
public class MemberRegisterServiceImpl implements MemberRegisterService {

    /**
     * 会員情報リポジトリ。
     */
    @Inject
    MemberRepository memberRepository;

    /**
     * パスワードをハッシュ化するためのエンコーダ。
     */
    @Inject
    PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public Member register(Member member) {
        Assert.notNull(member);

        MemberLogin memberLogin = member.getMemberLogin();
        Assert.notNull(memberLogin);

        // パスワードをエンコードする。
        String hashedPassword = passwordEncoder.encode(member.getMemberLogin()
                .getPassword());

        memberLogin.setPassword(hashedPassword);
        memberLogin.setLastPassword(hashedPassword);
        memberLogin.setLoginFlg(false);

        // 入力されたユーザ情報を基に、データベースへの登録を行う。
        // MyBatis3の機能(SelectKey)によりmemberにはcustomerNoが格納される。
        int insertMemberCount = memberRepository.insert(member);
        if (insertMemberCount != 1) {
            throw new SystemException(LogMessages.E_AR_A0_L9002
                    .getCode(), LogMessages.E_AR_A0_L9002.getMessage(
                            insertMemberCount, 1));
        }

        int insertMemberLoginCount = memberRepository.insertMemberLogin(member);
        if (insertMemberLoginCount != 1) {
            throw new SystemException(LogMessages.E_AR_A0_L9002
                    .getCode(), LogMessages.E_AR_A0_L9002.getMessage(
                            insertMemberLoginCount, 1));
        }
        return member;
    }

}
