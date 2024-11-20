import './App.css'
import {VideoContainer} from "./VideoContainer.jsx";
import {Component} from "react";
import {dummyData} from "./utils/dummyData.js";

class App extends Component {
    constructor() {
        super();
        this.state = {
            fetchVideoDetails: () => {
                const data = dummyData[Math.floor(Math.random() * dummyData.length)]
                console.log(data)
                this.setState({
                    videoDetails: data
                })
            },
            videoDetails: null,
        }
    }

    componentDidMount() {
        this.state.fetchVideoDetails()
    }

    render() {
        if (this.state.videoDetails === null) return <div>Loading...</div>

        return (
            <div>
                <h1>{this.state.videoDetails["videoId"]}</h1>
                <VideoContainer videoInfo={this.state.videoDetails} fetchNextVideo={this.state.fetchVideoDetails}/>
            </div>
        )
    }
}

export default App
