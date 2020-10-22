Feature('answer');

Scenario('Answer with logged out user', ({ I }) => {
    I.amOnPage('/question/9603ce27-77c2-43c1-8c58-e01ca3c07242')
    //Redirected when user not connected
    I.seeInCurrentUrl('/login')
});

Scenario('Answer with logged in user', ( {I, login} ) => {
    login('user')
    I.amOnPage('/question/18dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.fillField('Rich Text Editor, main', 'tralalilalaaa')
    I.click('Ajouter')
    I.seeInCurrentUrl('/question/18dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('tralalilalaaa')
});

Scenario('Answer with logged user', ( {I, login} ) => {
    login('user');
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.fillField('Rich Text Editor, main', 'I dont understand this question')
    I.click('Ajouter')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('I dont understand this question')
});

