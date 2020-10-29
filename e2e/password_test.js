Feature('password');

Before(({login}) => {
    login('user')
});

Scenario('test changing with same password', ({ I }) => {
    I.amOnPage('/changePassword')
    I.fillField('oldPassword', 'gil')
    I.fillField('newPassword', 'gil')
    I.fillField('newPasswordAgain', 'gil')
    I.click('Changer')
    I.seeInCurrentUrl('/changePassword')
    I.see('Le nouveau mot de passe doit être différent de l\'ancien')
});

Scenario('test changing with wrong old password', ({ I }) => {
    I.amOnPage('/changePassword')
    I.fillField('oldPassword', 'test')
    I.fillField('newPassword', 'gil')
    I.fillField('newPasswordAgain', 'gil')
    I.click('Changer')
    I.seeInCurrentUrl('/changePassword')
    I.see('L\'ancien mot de passe ne correspond pas')
});

Scenario('test changing with no corresponding new passwords', ({ I }) => {
    I.amOnPage('/changePassword')
    I.fillField('oldPassword', 'gil')
    I.fillField('newPassword', '123')
    I.fillField('newPasswordAgain', '321')
    I.click('Changer')
    I.seeInCurrentUrl('/changePassword')
    I.see('Le deux nouveaux mots de passe doivent être pareils')
});

Scenario('test changing good password', ({ I }) => {
    I.amOnPage('/changePassword')
    I.fillField('oldPassword', 'gil')
    I.fillField('newPassword', 'test')
    I.fillField('newPasswordAgain', 'test')
    I.click('Changer')
    I.seeInCurrentUrl('/profile')
    I.see('Mot de passe modifié avec succès')
});