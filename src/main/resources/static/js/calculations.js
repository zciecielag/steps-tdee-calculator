function calculate(event) {
    event.preventDefault();

    let bmrResultElement = document.getElementById("bmrResult");
    let stepCalorieBurnResultElement = document.getElementById("stepResult");
    let tdeeResultElement = document.getElementById("tdeeResult");

    let weight = document.forms["calculateForm"]["weight"].value;
    let height = document.forms["calculateForm"]["height"].value;
    let age = document.forms["calculateForm"]["age"].value;
    let gender = document.forms["calculateForm"]["gender"].value;
    let paceToMET = document.forms["calculateForm"]["pace"].value;
    let steps = document.forms["calculateForm"]["steps"].value;
    let time = document.forms["calculateForm"]["time"].value;

    let bmr = calculateBMR(gender, weight, height, age);
    let stepsBurn = calculateStepCalorieBurn(paceToMET, steps, time, weight);
    let tdee = (bmr + stepsBurn);

    if (tdee < 1200) {
        tdee = 1200;
    }

    bmrResultElement.textContent = bmr.toFixed(2);
    stepCalorieBurnResultElement.textContent = stepsBurn.toFixed(2);
    tdeeResultElement.textContent = tdee.toFixed(2);

    alert("OK");
}

function calculateBMR(gender, weight, height, age) {

      // Mifflin St Jeor
      //Men 	BMR = (10 × weight in kg) + (6.25 × height in cm) – (5 × age in years) + 5
      //Women 	BMR = (10 × weight in kg) + (6.25 × height in cm) – (5 × age in years) – 161

      if (gender == "male") {
        return (10*weight) + (6.25*height) - (5*age) + 5;
      }
      return (10*weight) + (6.25*height) - (5*age) - 161;
}

function calculateStepCalorieBurn(paceToMET, steps, time, weight) {

    // https://www.healthline.com/nutrition/10000-steps-calories-burned#estimating-calories-burned
    // 0.0175 * MET(pace) * weight * minutes

    switch (paceToMET) {
        case 3.2:
            paceToMET = 2.8;
            break;
        case 4.8:
            paceToMET = 4.3;
            break;
        case 6.4:
            paceToMET = 5.0;
            break;
        case 8.0:
            paceToMET = 8.3;
            break;
        default:
            paceToMET = 2.8;
    }

    return ((0.0175*paceToMET*weight*time)/10000)*steps;
}

function redirectToRegisterWithValues() {
    let weight = document.forms["calculateForm"]["weight"].value;
    let height = document.forms["calculateForm"]["height"].value;
    let age = document.forms["calculateForm"]["age"].value;
    let gender = document.forms["calculateForm"]["gender"].value;
    let paceToMET = document.forms["calculateForm"]["pace"].value;
    let steps = document.forms["calculateForm"]["steps"].value;
    let time = document.forms["calculateForm"]["time"].value;

    let url = new URL("http://localhost:8080/registerForm")
    const urlParams = new URLSearchParams(url.search);

    urlParams.set('weight', weight);
    urlParams.set('height', height);
    urlParams.set('age', age);
    urlParams.set('gender', gender);
    urlParams.set('pace', paceToMET);
    urlParams.set('steps', steps);
    urlParams.set('time', time);

    url.search = urlParams;

    window.location.href = url;
}