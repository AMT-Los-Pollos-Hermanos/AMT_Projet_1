Feature('vote');

Before(({login}) => {
    login('user')
});

Scenario('test vote plus on question', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Question 2')
    I.see('0', '.q-vote')
    I.click('.q-vote-plus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('1', '.q-vote')
});

Scenario('test cancel vote on question', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Question 2')
    I.see('1', '.q-vote')
    I.click('.q-vote-plus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('0', '.q-vote')
});

Scenario('test vote minus on question', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Question 2')
    I.see('0', '.q-vote')
    I.click('.q-vote-minus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('-1', '.q-vote')
});

Scenario('test cancel minus vote on question', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Question 2')
    I.see('-1', '.q-vote')
    I.click('.q-vote-minus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('0', '.q-vote')
});

Scenario('test vote plus on comment', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Comment 3')
    I.see('0', '.c-vote')
    I.click('.c-vote-plus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('1', '.c-vote')
});

Scenario('test cancel vote plus on comment', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Comment 3')
    I.see('1', '.c-vote')
    I.click('.c-vote-plus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('0', '.c-vote')
});

Scenario('test vote minus on comment', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Comment 3')
    I.see('0', '.c-vote')
    I.click('.c-vote-minus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('-1', '.c-vote')
});

Scenario('test vote minus on comment', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Comment 3')
    I.see('-1', '.c-vote')
    I.click('.c-vote-minus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('0', '.c-vote')
});

Scenario('test vote plus on answer', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Reponse sur question 2')
    I.see('0', '.a-vote')
    I.click('.a-vote-plus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('1', '.a-vote')
});

Scenario('test cancel vote plus on answer', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Reponse sur question 2')
    I.see('1', '.a-vote')
    I.click('.a-vote-plus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('0', '.a-vote')
});

Scenario('test vote minus on answer', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Reponse sur question 2')
    I.see('0', '.a-vote')
    I.click('.a-vote-minus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('-1', '.a-vote')
});

Scenario('test vote minus on answer', ({ I }) => {
    I.amOnPage('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Reponse sur question 2')
    I.see('-1', '.a-vote')
    I.click('.a-vote-minus')
    I.seeInCurrentUrl('/question/58dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('0', '.a-vote')
});